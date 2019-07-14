public void onBind(){
  final Context context=context();
  final User user=this.activity.user();
  final Project project=this.activity.project();
  if (user != null && project != null) {
    this.activityTitleTextView.setVisibility(View.GONE);
    Picasso.with(context).load(user.avatar().small()).transform(new CircleTransformation()).into(this.activityImageView);
    this.activitySubtitleTextView.setText(Html.fromHtml(this.ksString.format(this.categoryBackingString,"friend_name",user.name(),"project_name",project.name(),"creator_name",project.creator().name())));
  }
}
