private void updateViews(@NonNull PullRequest pullRequest){
  setTitle(String.format("#%s",pullRequest.getNumber()));
  if (getSupportActionBar() != null) {
    getSupportActionBar().setSubtitle(pullRequest.getRepoId());
  }
  date.setText(SpannableBuilder.builder().append(getPresenter().getMergeBy(pullRequest,getApplicationContext())));
  size.setVisibility(View.GONE);
  User userModel=pullRequest.getUser();
  if (userModel != null) {
    title.setText(SpannableBuilder.builder().append(userModel.getLogin()).append("/").append(pullRequest.getTitle()));
    avatarLayout.setUrl(userModel.getAvatarUrl(),userModel.getLogin(),false,LinkParserHelper.isEnterprise(pullRequest.getUrl()));
  }
 else {
    title.setText(SpannableBuilder.builder().append(pullRequest.getTitle()));
  }
  detailsIcon.setVisibility(View.VISIBLE);
}
