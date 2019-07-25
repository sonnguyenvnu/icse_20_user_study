@Override public ReadableUser create(PersistableUser user,MerchantStore store){
  User userModel=new User();
  userModel=converPersistabletUserToUser(store,languageService.defaultLanguage(),userModel,user);
  if (CollectionUtils.isEmpty(userModel.getGroups())) {
    throw new ServiceRuntimeException("No valid group groups associated with user " + user.getUserName());
  }
  try {
    userService.saveOrUpdate(userModel);
    User createdUser=userService.getById(userModel.getId());
    return convertUserToReadableUser(createdUser.getDefaultLanguage(),createdUser);
  }
 catch (  ServiceException e) {
    throw new ServiceRuntimeException("Cannot create user " + user.getUserName() + " for store " + store.getCode(),e);
  }
}
