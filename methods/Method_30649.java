public void bind(final User userInfo,List<SimpleUser> followingList){
  final Context context=getContext();
  OnClickListener viewFollowingListListener=new OnClickListener(){
    @Override public void onClick(    View view){
      context.startActivity(FollowingListActivity.makeIntent(userInfo.getIdOrUid(),context));
    }
  }
;
  mTitleText.setOnClickListener(viewFollowingListListener);
  mViewMoreText.setOnClickListener(viewFollowingListListener);
  int i=0;
  for (  final SimpleUser user : followingList) {
    if (i >= USER_COUNT_MAX) {
      break;
    }
    if (i >= mFollowingList.getChildCount()) {
      ViewUtils.inflateInto(R.layout.profile_user_item,mFollowingList);
    }
    View userLayout=mFollowingList.getChildAt(i);
    UserLayoutHolder holder=(UserLayoutHolder)userLayout.getTag();
    if (holder == null) {
      holder=new UserLayoutHolder(userLayout);
      userLayout.setTag(holder);
    }
    ImageUtils.loadAvatar(holder.avatarImage,user.avatar);
    holder.nameText.setText(user.name);
    userLayout.setOnClickListener(view -> context.startActivity(ProfileActivity.makeIntent(user,context)));
    ++i;
  }
  ViewUtils.setVisibleOrGone(mFollowingList,i != 0);
  ViewUtils.setVisibleOrGone(mEmptyView,i == 0);
  if (userInfo.followingCount > i) {
    mViewMoreText.setText(context.getString(R.string.view_more_with_count_format,userInfo.followingCount));
  }
 else {
    mViewMoreText.setVisibility(GONE);
  }
  for (int count=mFollowingList.getChildCount(); i < count; ++i) {
    ViewUtils.setVisibleOrGone(mFollowingList.getChildAt(i),false);
  }
  if (userInfo.followerCount > 0) {
    mFollwerText.setText(context.getString(R.string.profile_follower_count_format,userInfo.followerCount));
    mFollwerText.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View view){
        context.startActivity(FollowerListActivity.makeIntent(userInfo.getIdOrUid(),context));
      }
    }
);
  }
 else {
    mFollwerText.setVisibility(GONE);
  }
}
