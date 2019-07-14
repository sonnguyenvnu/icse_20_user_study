@SuppressLint("ClickableViewAccessibility") @Override public void onInitViews(@Nullable User userModel){
  if (getView() != null) {
    TransitionManager.beginDelayedTransition((ViewGroup)getView());
  }
  if (this.userModel != null)   return;
  progress.setVisibility(View.GONE);
  if (userModel == null)   return;
  this.userModel=userModel;
  username.setText(InputHelper.isEmpty(userModel.getName()) ? userModel.getLogin() : userModel.getName());
  if (userModel.getDescription() != null) {
    description.setText(EmojiParser.parseToUnicode(userModel.getDescription()));
    description.setVisibility(View.VISIBLE);
  }
 else {
    description.setVisibility(GONE);
  }
  avatarLayout.setUrl(userModel.getAvatarUrl(),null,false,false);
  avatarLayout.findViewById(R.id.avatar).setOnTouchListener((v,event) -> {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      ActivityHelper.startCustomTab(getActivity(),userModel.getAvatarUrl());
      return true;
    }
    return false;
  }
);
  location.setText(userModel.getLocation());
  email.setText(userModel.getEmail());
  link.setText(userModel.getBlog());
  joined.setText(ParseDateFormat.getTimeAgo(userModel.getCreatedAt()));
  if (!InputHelper.isEmpty(userModel.getLocation())) {
    location.setVisibility(View.VISIBLE);
  }
  if (!InputHelper.isEmpty(userModel.getEmail())) {
    email.setVisibility(View.VISIBLE);
  }
  if (!InputHelper.isEmpty(userModel.getBlog())) {
    link.setVisibility(View.VISIBLE);
  }
  if (!InputHelper.isEmpty(userModel.getCreatedAt())) {
    joined.setVisibility(View.VISIBLE);
  }
  if (!InputHelper.isEmpty(userModel.getEmail())) {
    email.setVisibility(View.VISIBLE);
  }
  projects.setVisibility(userModel.isHasOrganizationProjects() ? View.VISIBLE : View.GONE);
}
