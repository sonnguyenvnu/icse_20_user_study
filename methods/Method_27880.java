@SuppressLint("ClickableViewAccessibility") @Override public void onInitViews(@Nullable User userModel){
  progress.setVisibility(GONE);
  if (userModel == null)   return;
  if (profileCallback != null)   profileCallback.onCheckType(userModel.isOrganizationType());
  if (getView() != null) {
    if (this.userModel == null) {
      TransitionManager.beginDelayedTransition((ViewGroup)getView(),new AutoTransition().addListener(new Transition.TransitionListener(){
        @Override public void onTransitionStart(        @NonNull Transition transition){
        }
        @Override public void onTransitionEnd(        @NonNull Transition transition){
          if (contributionView != null)           getPresenter().onLoadContributionWidget(contributionView);
        }
        @Override public void onTransitionCancel(        @NonNull Transition transition){
        }
        @Override public void onTransitionPause(        @NonNull Transition transition){
        }
        @Override public void onTransitionResume(        @NonNull Transition transition){
        }
      }
));
    }
 else {
      getPresenter().onLoadContributionWidget(contributionView);
    }
  }
  this.userModel=userModel;
  followBtn.setVisibility(!isMeOrOrganization() ? VISIBLE : GONE);
  username.setText(userModel.getLogin());
  fullname.setText(userModel.getName());
  if (userModel.getBio() != null) {
    description.setText(EmojiParser.parseToUnicode(userModel.getBio()));
  }
 else {
    description.setVisibility(GONE);
  }
  avatarLayout.setUrl(userModel.getAvatarUrl(),null,false,false,true);
  avatarLayout.findViewById(R.id.avatar).setOnTouchListener((v,event) -> {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      ActivityHelper.startCustomTab(getActivity(),userModel.getAvatarUrl());
      return true;
    }
    return false;
  }
);
  organization.setText(userModel.getCompany());
  location.setText(userModel.getLocation());
  email.setText(userModel.getEmail());
  link.setText(userModel.getBlog());
  joined.setText(ParseDateFormat.getTimeAgo(userModel.getCreatedAt()));
  if (InputHelper.isEmpty(userModel.getCompany())) {
    organization.setVisibility(GONE);
  }
  if (InputHelper.isEmpty(userModel.getLocation())) {
    location.setVisibility(GONE);
  }
  if (InputHelper.isEmpty(userModel.getEmail())) {
    email.setVisibility(GONE);
  }
  if (InputHelper.isEmpty(userModel.getBlog())) {
    link.setVisibility(GONE);
  }
  if (InputHelper.isEmpty(userModel.getCreatedAt())) {
    joined.setVisibility(GONE);
  }
  followers.setText(SpannableBuilder.builder().append(getString(R.string.followers)).append(" (").bold(String.valueOf(userModel.getFollowers())).append(")"));
  following.setText(SpannableBuilder.builder().append(getString(R.string.following)).append(" (").bold(String.valueOf(userModel.getFollowing())).append(")"));
}
