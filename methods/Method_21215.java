@Override protected @NonNull KSViewHolder viewHolder(final @LayoutRes int layout,final @NonNull View view){
switch (layout) {
case R.layout.discovery_onboarding_view:
    return new DiscoveryOnboardingViewHolder(view,this.delegate);
case R.layout.project_card_view:
  return new ProjectCardViewHolder(view,this.delegate);
case R.layout.activity_sample_friend_backing_view:
return new ActivitySampleFriendBackingViewHolder(view,this.delegate);
case R.layout.activity_sample_friend_follow_view:
return new ActivitySampleFriendFollowViewHolder(view,this.delegate);
case R.layout.activity_sample_project_view:
return new ActivitySampleProjectViewHolder(view,this.delegate);
default :
return new EmptyViewHolder(view);
}
}
