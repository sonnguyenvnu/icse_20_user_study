@Override public void onBind(){
  final Context context=context();
  final Project project=activity().project();
  if (project == null) {
    return;
  }
  final User user=activity().user();
  if (user == null) {
    return;
  }
  final Photo photo=project.photo();
  if (photo == null) {
    return;
  }
  final Update update=activity().update();
  if (update == null) {
    return;
  }
  final DateTime publishedAt=ObjectUtils.coalesce(update.publishedAt(),new DateTime());
  this.projectNameTextView.setText(project.name());
  Picasso.with(context).load(photo.little()).into(this.projectPhotoImageView);
  this.timestampTextView.setText(DateTimeUtils.relative(context,this.ksString,publishedAt));
  this.updateBodyTextView.setText(update.truncatedBody());
  this.updateSequenceTextView.setText(this.ksString.format(this.projectUpdateCountString,"update_count",String.valueOf(update.sequence())));
  this.updateTitleTextView.setText(update.title());
}
