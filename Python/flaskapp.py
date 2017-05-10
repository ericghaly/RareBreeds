import os
from flask import Flask, render_template, session, redirect, url_for, flash
from flask_script import Manager
from flask_bootstrap import Bootstrap
from flask_moment import Moment
from flask_wtf import Form
from wtforms import StringField, SubmitField, SelectField, PasswordField, IntegerField
from wtforms.validators import Required, DataRequired
from flask_sqlalchemy import SQLAlchemy

basedir = os.path.abspath(os.path.dirname(__file__))

app = Flask(__name__)
app.config['SECRET_KEY'] = 'hard to guess string'
app.config['SQLALCHEMY_DATABASE_URI'] =\
    'sqlite:///' + os.path.join(basedir, 'data.sqlite')
app.config['SQLALCHEMY_COMMIT_ON_TEARDOWN'] = True

manager = Manager(app)
bootstrap = Bootstrap(app)
moment = Moment(app)
db = SQLAlchemy(app)

def my_function_Breedtable():
    """This class will set up the strucure for the breed table

    backrefrence to posts
    """
    pass

class Breed(db.Model):
    __tablename__ = 'breeds'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(64), unique=True)
    posts = db.relationship('Post', backref='breeds', lazy='dynamic')

    def __repr__(self):
        return '<Breed %r>' % self.name

def my_function_Usertable():
    """This class will set up the user table

    has backrefs to posts/comments

    -one to many relationship
    """
    pass
class User(db.Model):
    __tablename__ = 'users'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(64), unique=True)
    password = db.Column(db.String(64))
    homeTown = db.Column(db.Integer)
    bio = db.Column(db.String(64))
    is_breeder = db.Column(db.Boolean)
    posts = db.relationship('Post', backref='users', lazy='dynamic')
    comments = db.relationship('Comment', backref='users', lazy = 'dynamic')

    def __repr__(self):
        return '<User %r>' % self.name

def my_function_post_table():
    """This class will set up the post table

    has forigen keys -
    1. breed_id
    2.user_id

    backref to comment (on to many relationship)
    """
    pass
class Post(db.Model):
    __tablename__ = 'posts'
    id = db.Column(db.Integer, primary_key=True)
    breed_id = db.Column(db.Integer, db.ForeignKey('breeds.id'))
    user_id = db.Column(db.Integer, db.ForeignKey('users.id'))
    name = db.Column(db.String(64))
    content = db.Column(db.String(64))
    votes = db.Column(db.Integer)
    sold = db.Column(db.Boolean)
    comments = db.relationship('Comment', backref='posts', lazy='dynamic')

    def __repr__(self):
        return '<Post %r>' % self.name

def my_function_CommentTable():
    """This class will set up the comment table

    has forigen keys:
    1. posts_id
    2.user_id

    """
    pass

def my_function_comment_form():
    """This form will take in

    1.id
    2.content of comment
    3.who poseted the comment
    4.post_id of comment
    5. user_id of poster

    """
    pass
class Comment(db.Model):
    __tablename__ = 'comments'
    id = db.Column(db.Integer, primary_key=True)
    content = db.Column(db.String(64))
    whoPosted = db.Column(db.String(64))
    posts_id = db.Column(db.Integer, db.ForeignKey('posts.id'))
    user_id = db.Column(db.Integer, db.ForeignKey('users.id'))

    def __repr__(self):
        return '<Comment %r>' % self.name

class Message(db.Model):
    __tablename__ = 'messages'
    id = db.Column(db.Integer, primary_key=True)
    content = db.Column(db.String(64))
    whoPosted = db.Column(db.String(64))
    whoPostedID = db.Column(db.String(64))
    user_id = db.Column(db.Integer, db.ForeignKey('users.id'))

    def __repr__(self):
        return '<Message %r>' % self.name

def my_function_postform():
    """This form will take in
    1.breed neame
    2.name of the post itself
    3.content of post

    """
    pass
class PostForm(Form):
    breed = SelectField('Breed', coerce=int, validators=[DataRequired()])
    name = StringField('Name of Post?', validators=[Required()])
    content = StringField('Content?', validators=[Required()])
    submit = SubmitField('Submit')

class CommentForm(Form):
    content = StringField('Comment?', validators=[Required()])
    submit = SubmitField('Submit')

class MessageForm(Form):
    content = StringField('Message Text?', validators=[Required()])
    submit = SubmitField('Submit')

def my_function_Breedersignup():
    """This form will take in
    1.email for username
    2.hometown
    3.bio
    4.password

    """
    pass
class BreederSignUpForm(Form):
    email = StringField('What is your email?', validators=[Required()])
    hometown = IntegerField('What is your Zip Code?', validators=[Required()])
    bio = StringField('What is your description?', validators=[Required()])
    password1 = PasswordField('Password', validators=[Required()])
    password2 = PasswordField('Password', validators=[Required()])
    submit = SubmitField('Submit')

def my_function_login():
    """This form will take

    1.email and passoword
    """
    pass

class LoginForm(Form):
    email = StringField('What is your email?', validators=[Required()])
    password = PasswordField('Password', validators=[Required()])
    submit = SubmitField('Submit')

class BreedForm(Form):
    name = StringField('What is your Breed name?', validators=[Required()])
    submit = SubmitField('Submit')

class SearchForm(Form):
    name = StringField('Enter a breed to search for', validators=[Required()])
    submit = SubmitField('Submit')

class SellForm(Form):
    submit = SubmitField('Sell Dog')



@app.errorhandler(404)
def page_not_found(e):
    return render_template('404.html'), 404


@app.errorhandler(500)
def internal_server_error(e):
    return render_template('500.html'), 500

def my_function_New_Post():
    """This function will create a new post that will be entered into the database

    function will

    !. make a post for given breed
    """
    pass

@app.route('/New Post', methods=['GET', 'POST'])
def NewPost():
    form = PostForm()
    form.breed.choices = [(a.id, a.name) for a in Breed.query.order_by(Breed.name)]
    name = session['user']
    userDetails = User.query.filter_by(name=name).first()
    if form.validate_on_submit():
        newPost = Post(breed_id=form.breed.data, user_id = userDetails.id, name=form.name.data, sold = False, content=form.content.data)
        db.session.add(newPost)
        flash('Added Post to database!')
        return redirect(url_for('NewPost'))
    return render_template('New Post.html', form=form)

@app.route('/New Message/<id>', methods=['GET', 'POST'])
def NewMessage(id):
    form = MessageForm()
    name = session['user']
    userDetails = User.query.filter_by(name=name).first()
    if form.validate_on_submit():
        newMessage = Message(content=form.content.data, whoPosted = userDetails.name, user_id=id, whoPostedID = userDetails.id)
        db.session.add(newMessage)
        flash('Added Message to database!')
        return redirect(url_for('NewMessage', id = id))
    return render_template('New Message.html', id = id,  form=form)

def my_function_ShowPost():
    """This function will show the specifiec post. User an create comment

    function parameters is the id of the post that is clicked on

    !. Will display post
    2.abilty to comment on post
    """
    pass

@app.route('/Show Post/<id>', methods=['GET', 'POST'])
def ShowPost(id):
    form = CommentForm()
    Posts = Post.query.filter_by(id=id).first()
    name = session['user']
    userDetails = User.query.filter_by(name=name).first()
    postComments = Comment.query.filter_by(posts_id=id)
    if form.validate_on_submit():
        newComment = Comment(posts_id=id, user_id=userDetails.id, content=form.content.data, whoPosted = userDetails.name)
        db.session.add(newComment)
        flash('Added Comment to database!')
        return redirect(url_for('ShowPost' , postDetails=Posts , id=id, postComments=postComments))
    return render_template('Show Post.html', postDetails=Posts, id=id, postComments=postComments, form=form)

def my_function_NewBreed():
    """This function will add a new breed to the database


    """
    pass

@app.route('/New Breed', methods=['GET', 'POST'])
def NewBreed():
    form = BreedForm()
    if form.validate_on_submit():
        newBreed = Breed.query.filter_by(name=form.name.data).first()
        if newBreed is None:
            newBreed = Breed(name=form.name.data)
            db.session.add(newBreed)
            flash('Added Breed to database!')
        return redirect(url_for('NewBreed'))
    return render_template('New Breed.html', form=form)

def my_function_Userbreeder():
    """This function will create a new breeder and add it to the database

    Will set session to logged in aswell as is_breeder to True

    """
    pass

@app.route('/New Breeder', methods=['GET', 'POST'])
def NewBreeder():
    form = BreederSignUpForm()
    if form.validate_on_submit():
        newBreeder = User.query.filter_by(name=form.email.data).first()
        if newBreeder is None and (form.password1.data == form.password2.data):
            newBreeder = User(name=form.email.data, password=form.password1.data, homeTown=form.hometown.data, bio=form.bio.data, is_breeder = True)
            session['user'] = form.email.data
            session['logged_in'] = True
            session['is_breeder'] = True
            db.session.add(newBreeder)
            flash('Added Breeder to database!')
        return redirect(url_for('UserPage'))
    return render_template('New Breeder.html', form=form)

def my_function_Login():
    """This function will log he user in

    function will

    !. log user in
    2. will be able to handle bad inputs
    3. sets session variable
    """
    pass

@app.route('/Login', methods=['GET', 'POST'])
def Login():
    form = LoginForm()
    if form.validate_on_submit():
        userName = User.query.filter_by(name=form.email.data).first()
        if userName == None:
            flash('Wrong Login Details!')
            return render_template('Login.html', form=form)
        if (form.password.data == userName.password):
            session['user'] = form.email.data
            session['logged_in'] = True
            session['is_breeder'] = userName.is_breeder
            return redirect(url_for('UserPage'))
        flash('Wrong Login Details!')
    return render_template('Login.html', form=form)

def my_function_Logout():
    """This function will log the user out
    """
    pass

@app.route('/Logout', methods=['GET', 'POST'])
def Logout():
    session['logged_in'] = False
    return render_template('index.html')

def my_function_NewUser():
    """This function will add a new user to the database. Not a breeder

    1.will set logged in to true and redirect to user page
    """
    pass

@app.route('/New User', methods=['GET', 'POST'])
def NewUser():
    form = BreederSignUpForm()
    if form.validate_on_submit():
        newUser = User.query.filter_by(name=form.email.data).first()
        if newUser is None and (form.password1.data == form.password2.data):
            newUser = User(name=form.email.data, password=form.password1.data, homeTown=form.hometown.data, bio=form.bio.data, is_breeder = False)
            session['user'] = form.email.data
            session['logged_in'] = True
            db.session.add(newUser)
            flash('Added User to database!')
        return redirect(url_for('UserPage'))
    return render_template('New User.html', form=form)


def my_function_UserPage():
    """This function will get the details for the user page and return them to the view template

    function will

    !. take a user name from the session variable
    2. return user data
    """
    pass

@app.route('/User Page', methods=['GET', 'POST'])
def UserPage():
    name = session['user']
    allDetails = User.query.filter_by(name=name).first()
    userPosts = Post.query.filter_by(user_id=allDetails.id)
    userMessages = Message.query.filter_by(user_id = allDetails.id)
    return render_template('User Page.html', allDetails=allDetails, userPosts = userPosts, userMessages = userMessages)

def my_function_Sell_Dog_List():
    """This function will return a list of dogs that the user posted and are for sale

    function will

    !. take a user name from the session variable
    2. return user data
    3. return post data
    """
    pass

@app.route('/Sell Dog List', methods=['GET', 'POST'])
def SellDogList():
    name = session['user']
    allDetails = User.query.filter_by(name=name).first()
    userPosts = Post.query.filter_by(user_id=allDetails.id)
    return render_template('Sell Dog List.html', allDetails=allDetails, userPosts = userPosts)

def my_function_Show_Post_Sell():
    """This function will show the specific post to be sold

        function parameters is the id of the post that is clicked on

        !. Will display post
        2.ability to sell posted Dog
        """
    pass

@app.route('/Show Post Sell/<id>', methods=['GET', 'POST'])
def ShowPostSell(id):
    form = SellForm()
    Posts = Post.query.filter_by(id=id).first()
    name = session['user']
    postComments = Comment.query.filter_by(posts_id=id)
    if form.validate_on_submit():
        post = Post.query.filter_by(id=id).first()
        post.sold = True
        db.session.commit()
        flash('Sold Dog')
        return redirect(url_for('ShowPost' , postDetails=Posts , id=id, postComments=postComments))
    return render_template('Show Post Sell.html', postDetails=Posts, id=id, postComments=postComments, form=form)



def my_function_All_Boards():
    """This function will get a list of all Boards. It achieves this by querying the database for all boards
    function will

    function will:
    1.query the database for all boards
    2. return list of all boards
    """
    pass

@app.route('/All Boards', methods=['GET', 'POST'])
def AllBoards():
    allBoards = Breed.query.all()
    return render_template('All Boards.html', allBoards=allBoards)


def my_function_Board():
    """This function will get a specifies board that is clicked on. If you click on the Labrador board you will be taken there


    function will

    !. take parmeter of id
    2. return information about this board
    """
    pass

@app.route('/Board/<id>', methods=['GET', 'POST'])
def Board(id):
    Posts = Post.query.filter_by(breed_id=id)
    thisBoard = Breed.query.filter_by(id=id).first()
    return render_template('Board.html', Posts=Posts, thisBoard=thisBoard)


def my_function_userOrBreeder():
    """This function will get throw a webpage to chose weather you are a regular user or a breeder



    """
    pass
@app.route('/UserOrBreeder', methods=['GET', 'POST'])
def UserOrBreeder():
    return render_template('UserOrBreeder.html')

def my_function_BreederList():
    """This function will get a list of all breeders. It achieves this by querying the database for all breeders as well as breeders with a certain area code


    function will

    !. take a breeder name from the session variable
    2. return breeder data
    """
    pass

@app.route('/Breeder List', methods=['GET'])
def BreederList():
    name = session['user']
    userDetails = User.query.filter_by(name=name).first()
    allBreeders = User.query.filter_by(is_breeder=True)
    localBreeders = User.query.filter_by(homeTown = userDetails.homeTown, is_breeder=True)
    return render_template('Breeder List.html', allBreeders=allBreeders, localBreeders = localBreeders)


def my_function_BreederDetails():
    """This function will get the details of a specific breeder by name. This name will be pased in via the function peramters.
    function will then return the data for this breeder to be displayed

    function will

    !. take a breeder name
    2. return breeder data
    3. return posts by breeder
    """
    pass

@app.route('/Breeder Details/<name>', methods=['GET', 'POST'])
def BreederDetails(name):
    allBreeders = User.query.filter_by(name = name).first()
    BreederPosts = Post.query.filter_by(user_id=allBreeders.id)
    return render_template('Breeder Details.html', allBreeders=allBreeders, breederPosts=BreederPosts)

def my_function_search():
    """This function will search al the various breeds

    function will

    !. take input from the user
    2. query the database
    3. return all the breeds pertaining to the search term
    4. will handle null or bad data input
    """
    pass

@app.route('/Search', methods=['GET', 'POST'])
def Search():
    form = SearchForm()
    if form.validate_on_submit():
        breed = Breed.query.filter_by(name=form.name.data).first()
        if breed is None:
            flash('could not find breed')
            return render_template('Search.html', form=form)
        if breed is not None:
            Posts = Post.query.filter_by(breed_id= breed.id)
            thisBoard = Breed.query.filter_by(id=breed.id).first()
        return redirect(url_for('Board', Posts=Posts, id=breed.id, thisBoard=thisBoard ))
    return render_template('Search.html', form=form)

def my_function_index():
    """Simply returns the home page
    """
    pass

@app.route('/', methods=['GET', 'POST'])
def index():
    return render_template('index.html')

def printDocs():
    print(my_function_index.__doc__)
    print(my_function_search.__doc__)
    print(my_function_BreederDetails.__doc__)
    print(my_function_index.__doc__)
    print(my_function_Usertable.__doc__)
    print(my_function_Breedtable.__doc__)
    print(my_function_post_table.__doc__)
    print(my_function_CommentTable.__doc__)
    print(my_function_comment_form.__doc__)
    print(my_function_postform.__doc__)
    print(my_function_Breedersignup.__doc__)
    print(my_function_login.__doc__)
    print(my_function_New_Post.__doc__)
    print(my_function_ShowPost.__doc__)
    print(my_function_NewBreed.__doc__)
    print(my_function_Userbreeder.__doc__)
    print(my_function_Login.__doc__)
    print(my_function_Logout.__doc__)
    print(my_function_NewUser.__doc__)
    print(my_function_UserPage.__doc__)
    print(my_function_All_Boards.__doc__)
    print(my_function_Board.__doc__)
    print(my_function_userOrBreeder.__doc__)
    print(my_function_BreederList.__doc__)
    print(my_function_BreederDetails.__doc__)
    print(my_function_search.__doc__)
    print(my_function_index.__doc__)
    print(my_function_Sell_Dog_List.__doc__)
    print(my_function_Show_Post_Sell.__doc__)

if __name__ == '__main__':
    printDocs()
    app.run()








