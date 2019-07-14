/** 
 * @param user
 * @return
 */
public boolean isUserCorrect(User user){
  return user != null && user.getId() > 0;
}
