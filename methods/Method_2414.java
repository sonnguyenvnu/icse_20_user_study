/** 
 * ??username??UpmsUser
 * @param username
 * @return
 */
@Override public UpmsUser selectUpmsUserByUsername(String username){
  UpmsUserExample upmsUserExample=new UpmsUserExample();
  upmsUserExample.createCriteria().andUsernameEqualTo(username);
  List<UpmsUser> upmsUsers=upmsUserMapper.selectByExample(upmsUserExample);
  if (null != upmsUsers && upmsUsers.size() > 0) {
    return upmsUsers.get(0);
  }
  return null;
}
