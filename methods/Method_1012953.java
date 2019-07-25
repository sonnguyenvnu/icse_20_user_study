@Override public void validate(){
  assertTrue(usersToRemind != null,"List of users to remind cannot be null");
  assertTrue(usersToRemind.length != 0,"List of users to remind cannot be empty");
}
