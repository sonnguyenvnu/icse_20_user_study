public void greet(){
  User user=userDao.getForUsername(username);
  if (user != null) {
    greeting="Hello, " + user.getFirstName() + " " + user.getLastName() + "!";
  }
 else {
    greeting="No such user exists! Use 'emuster' or 'jdoe'";
  }
}
