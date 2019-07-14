public void bind(@NonNull User user,boolean isContributor){
  avatar.setUrl(user.getAvatarUrl(),user.getLogin(),user.isOrganizationType(),LinkParserHelper.isEnterprise(user.getHtmlUrl()));
  title.setText(user.getLogin());
  date.setVisibility(!isContributor ? View.GONE : View.VISIBLE);
  if (isContributor) {
    date.setText(String.format("%s (%s)",date.getResources().getString(R.string.commits),user.getContributions()));
  }
}
