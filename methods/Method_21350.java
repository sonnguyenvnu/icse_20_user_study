@Override public void onBind(){
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
  Picasso.with(context()).load(photo.little()).into(this.projectPhotoImageView);
switch (activity().category()) {
case Activity.CATEGORY_FAILURE:
    this.titleTextView.setText(this.ksString.format(this.projectNotSuccessfullyFundedString,"project_name",project.name()));
  break;
case Activity.CATEGORY_CANCELLATION:
this.titleTextView.setText(this.ksString.format(this.projectCanceledByCreatorString,"project_name",project.name()));
break;
case Activity.CATEGORY_SUSPENSION:
this.titleTextView.setText(this.ksString.format(this.projectSuspendedString,"project_name",project.name()));
break;
default :
this.titleTextView.setText("");
}
}
