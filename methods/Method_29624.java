@Override public List<AppUser> getByAppId(Long appId){
  if (appId == null || appId < 0) {
    return Collections.emptyList();
  }
  List<AppUser> resultList=new ArrayList<AppUser>();
  List<AppToUser> appToUsers=appToUserDao.getByAppId(appId);
  if (appToUsers != null && appToUsers.size() > 0) {
    for (    AppToUser appToUser : appToUsers) {
      Long userId=appToUser.getUserId();
      if (userId == null) {
        continue;
      }
      AppUser user=appUserDao.get(userId);
      if (user == null) {
        continue;
      }
      resultList.add(user);
    }
  }
  return resultList;
}
