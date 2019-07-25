/** 
 * <p> Authenticates current user with 'username' against user data store </p> <p> Starts the new conversation. </p>
 * @param username the username of the user to authenticate
 */
public void authenticate(String username){
  if (isLogged()) {
    throw new IllegalStateException("User is logged and tries to authenticate again");
  }
  User user=userDao.getForUsername(username);
  if (user == null) {
    user=createUser(username);
  }
  authentication.setCurrentUser(user);
  conversation.begin();
}
