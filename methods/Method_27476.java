@Override public void bind(@NonNull Repo repo){
  if (repo.isFork() && !isStarred) {
    title.setText(SpannableBuilder.builder().append(" " + forked + " ",new LabelSpan(forkColor)).append(" ").append(repo.getName(),new LabelSpan(Color.TRANSPARENT)));
  }
 else   if (repo.isPrivateX()) {
    title.setText(SpannableBuilder.builder().append(" " + privateRepo + " ",new LabelSpan(privateColor)).append(" ").append(repo.getName(),new LabelSpan(Color.TRANSPARENT)));
  }
 else {
    title.setText(!isStarred ? repo.getName() : repo.getFullName());
  }
  if (withImage) {
    String avatar=repo.getOwner() != null ? repo.getOwner().getAvatarUrl() : null;
    String login=repo.getOwner() != null ? repo.getOwner().getLogin() : null;
    boolean isOrg=repo.getOwner() != null && repo.getOwner().isOrganizationType();
    if (avatarLayout != null) {
      avatarLayout.setVisibility(View.VISIBLE);
      avatarLayout.setUrl(avatar,login,isOrg,LinkParserHelper.isEnterprise(repo.getHtmlUrl()));
    }
  }
  long repoSize=repo.getSize() > 0 ? (repo.getSize() * 1000) : repo.getSize();
  size.setText(Formatter.formatFileSize(size.getContext(),repoSize));
  NumberFormat numberFormat=NumberFormat.getNumberInstance();
  stars.setText(numberFormat.format(repo.getStargazersCount()));
  forks.setText(numberFormat.format(repo.getForks()));
  date.setText(ParseDateFormat.getTimeAgo(repo.getUpdatedAt()));
  if (!InputHelper.isEmpty(repo.getLanguage())) {
    language.setText(repo.getLanguage());
    language.setTextColor(ColorsProvider.getColorAsColor(repo.getLanguage(),language.getContext()));
    language.setVisibility(View.VISIBLE);
  }
 else {
    language.setTextColor(Color.BLACK);
    language.setVisibility(View.GONE);
    language.setText("");
  }
}
