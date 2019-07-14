/** 
 * ?????????????friendIdList??
 * @param user0
 * @param id1
 * @return
 */
public static boolean isFriend(User user0,long id1){
  if (id1 <= 0) {
    return false;
  }
  long id0=user0 == null ? 0 : user0.getId();
  if (id0 <= 0) {
    return false;
  }
  return isContain(user0.getContactIdList(),id1);
}
