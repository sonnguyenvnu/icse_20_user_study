public void onBind(){
  final Context context=context();
  final Project project=this.activity.project();
  if (project != null) {
    final Photo photo=project.photo();
    if (photo != null) {
      Picasso.with(context).load(photo.little()).into(this.activityImageView);
    }
    this.activityTitleTextView.setText(project.name());
switch (this.activity.category()) {
case Activity.CATEGORY_FAILURE:
      this.activitySubtitleTextView.setText(this.categoryFailureString);
    break;
case Activity.CATEGORY_CANCELLATION:
  this.activitySubtitleTextView.setText(this.categoryCancellationString);
break;
case Activity.CATEGORY_LAUNCH:
final User user=this.activity.user();
if (user != null) {
this.activitySubtitleTextView.setText(this.ksString.format(this.categoryLaunchString,"user_name",user.name()));
}
break;
case Activity.CATEGORY_SUCCESS:
this.activitySubtitleTextView.setText(this.categorySuccessString);
break;
case Activity.CATEGORY_UPDATE:
final Update update=this.activity.update();
if (update != null) {
this.activitySubtitleTextView.setText(this.ksString.format(this.categoryUpdateString,"update_number",String.valueOf(update.sequence()),"update_title",update.title()));
}
break;
default :
break;
}
}
}
