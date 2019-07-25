/** 
 * Logs current user out and ends the current conversation.
 */
public void logout(){
  authentication.setCurrentUser(null);
  conversation.end();
}
