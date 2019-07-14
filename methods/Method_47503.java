/** 
 * @param username
 * @return
 */
@Override public User getUserByUserName(String username){
  return userJpaDao.getByUsernameIs(username);
}
