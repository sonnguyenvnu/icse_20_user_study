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
switch (activity().category()) {
case Activity.CATEGORY_LAUNCH:
    final DateTime launchedAt=coalesce(project.launchedAt(),new DateTime());
  this.cardView.setCardBackgroundColor(this.blueDarken10Color);
this.leftStatFirstTextView.setText(this.ksCurrency.format(project.goal(),project));
this.leftStatSecondTextView.setText(this.goalString);
this.rightStatFirstTextView.setText(this.launchedString);
this.rightStatSecondTextView.setText(DateTimeUtils.mediumDate(launchedAt));
this.titleTextView.setText(this.ksString.format(this.creatorLaunchedProjectString,"creator_name",user.name(),"project_name",project.name()));
break;
case Activity.CATEGORY_SUCCESS:
this.cardView.setCardBackgroundColor(this.greenDarken10Color);
this.leftStatFirstTextView.setText(this.ksCurrency.format(project.pledged(),project));
this.leftStatSecondTextView.setText(this.ksString.format(this.pledgedOfGoalString,"goal",this.ksCurrency.format(project.goal(),project)));
this.rightStatFirstTextView.setText(this.fundedString);
this.rightStatSecondTextView.setText(DateTimeUtils.mediumDate(activity().createdAt()));
this.titleTextView.setText(this.ksString.format(this.projectSuccessfullyFundedString,"project_name",project.name()));
break;
default :
this.cardView.setCardBackgroundColor(this.greenDarken10Color);
this.leftStatFirstTextView.setText("");
this.leftStatSecondTextView.setText("");
this.rightStatFirstTextView.setText("");
this.rightStatSecondTextView.setText("");
this.titleTextView.setText("");
}
Picasso.with(context).load(photo.full()).into(this.projectPhotoImageView);
}
