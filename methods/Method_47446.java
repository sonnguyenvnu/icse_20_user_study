/** 
 * @param username
 * @return
 */
@Override public User getUserByName(String username){
  return userJpaDao.findByName(username);
}
