/** 
 * if (user == null) >> user = new User();
 * @return
 */
@NonNull public User getUser(){
  if (user == null) {
    user=new User(getMoment().getUserId());
  }
  return user;
}
