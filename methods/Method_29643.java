public List<String> getPhoneList(){
  List<String> phoneList=new ArrayList<String>();
  if (CollectionUtils.isNotEmpty(appUsers)) {
    for (    AppUser appUser : appUsers) {
      String mobile=appUser.getMobile();
      if (StringUtils.isNotBlank(mobile)) {
        phoneList.add(appUser.getMobile());
      }
    }
  }
  return phoneList;
}
