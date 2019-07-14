@Override public void bind(@NonNull User user){
  name.setText(user.getLogin());
  avatarLayout.setUrl(user.getAvatarUrl(),user.getLogin(),true,LinkParserHelper.isEnterprise(user.getUrl()));
}
