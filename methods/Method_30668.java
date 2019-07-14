public void bindUser(final User user){
  final Context context=getContext();
  if (!ViewUtils.isVisible(mAvatarImage)) {
    final String largeAvatar=user.getLargeAvatarOrAvatar();
    ImageUtils.loadProfileAvatarAndFadeIn(mAvatarImage,largeAvatar);
    mAvatarImage.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View view){
        context.startActivity(GalleryActivity.makeIntent(largeAvatar,context));
      }
    }
);
  }
  mToolbarUsernameText.setText(user.name);
  mUsernameText.setText(user.name);
  mSignatureText.setText(user.signature);
  mJoinTimeLocationText.setJoinTimeAndLocation(user.createTime,user.locationName);
  if (user.isOneself()) {
    TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(mFollowButton,R.drawable.edit_icon_white_24dp,0,0,0);
    mFollowButton.setText(R.string.profile_edit);
    mFollowButton.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View view){
        if (mListener != null) {
          mListener.onEditProfile(user);
        }
      }
    }
);
  }
 else {
    FollowUserManager followUserManager=FollowUserManager.getInstance();
    String userIdOrUid=user.getIdOrUid();
    if (followUserManager.isWriting(userIdOrUid)) {
      TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(mFollowButton,new WhiteIndeterminateProgressIconDrawable(context),null,null,null);
      mFollowButton.setText(followUserManager.isWritingFollow(userIdOrUid) ? R.string.user_following : R.string.user_unfollowing);
    }
 else {
      int followDrawableId;
      int followStringId;
      if (user.isFollowed) {
        if (user.isFollower) {
          followDrawableId=R.drawable.mutual_icon_white_24dp;
          followStringId=R.string.profile_following_mutual;
        }
 else {
          followDrawableId=R.drawable.ok_icon_white_24dp;
          followStringId=R.string.profile_following;
        }
      }
 else {
        followDrawableId=R.drawable.add_icon_white_24dp;
        followStringId=R.string.profile_follow;
      }
      TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(mFollowButton,followDrawableId,0,0,0);
      mFollowButton.setText(followStringId);
    }
    mFollowButton.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View view){
        if (mListener != null) {
          mListener.onFollowUser(user,!user.isFollowed);
        }
      }
    }
);
  }
  mFollowButton.setVisibility(VISIBLE);
}
