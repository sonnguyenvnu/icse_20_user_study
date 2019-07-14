@Override public void bind(@NonNull PinnedRepos pinnedRepos){
  Repo repo=pinnedRepos.getPinnedRepo();
  if (repo == null)   return;
  if (repo.isFork()) {
    title.setText(SpannableBuilder.builder().append(" " + forked + " ",new LabelSpan(forkColor)).append(" ").append(repo.getName(),new LabelSpan(Color.TRANSPARENT)));
  }
 else   if (repo.isPrivateX()) {
    title.setText(SpannableBuilder.builder().append(" " + privateRepo + " ",new LabelSpan(privateColor)).append(" ").append(repo.getName(),new LabelSpan(Color.TRANSPARENT)));
  }
 else {
    title.setText(repo.getFullName());
  }
  String avatar=repo.getOwner() != null ? repo.getOwner().getAvatarUrl() : null;
  String login=repo.getOwner() != null ? repo.getOwner().getLogin() : null;
  boolean isOrg=repo.getOwner() != null && repo.getOwner().isOrganizationType();
  if (avatarLayout != null) {
    avatarLayout.setVisibility(View.VISIBLE);
    avatarLayout.setUrl(avatar,login,isOrg,LinkParserHelper.isEnterprise(repo.getHtmlUrl()));
  }
  if (stars != null && forks != null && date != null && language != null) {
    NumberFormat numberFormat=NumberFormat.getNumberInstance();
    stars.setText(numberFormat.format(repo.getStargazersCount()));
    forks.setText(numberFormat.format(repo.getForks()));
    date.setText(ParseDateFormat.getTimeAgo(repo.getUpdatedAt()));
    if (!InputHelper.isEmpty(repo.getLanguage())) {
      language.setText(repo.getLanguage());
      language.setTextColor(ColorsProvider.getColorAsColor(repo.getLanguage(),language.getContext()));
      language.setVisibility(View.VISIBLE);
    }
  }
}
