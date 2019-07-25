public boolean insert(User user) throws MyException {
  if (user == null) {
    return false;
  }
  if (user.getAvatarUrl() == null) {
    user.setAvatarUrl(Tools.getAvatar());
  }
  return super.insert(user);
}
