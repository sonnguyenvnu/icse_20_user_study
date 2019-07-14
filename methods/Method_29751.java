private SimpleUser makePartialUser(Account account){
  SimpleUser partialUser=new SimpleUser();
  partialUser.id=AccountUtils.getUserId(account);
  partialUser.uid=String.valueOf(partialUser.id);
  partialUser.name=AccountUtils.getUserName(account);
  return partialUser;
}
