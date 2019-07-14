private boolean isMeOrOrganization(){
  return Login.getUser() != null && Login.getUser().getLogin().equalsIgnoreCase(getPresenter().getLogin()) || (userModel != null && userModel.getType() != null && !userModel.getType().equalsIgnoreCase("user"));
}
