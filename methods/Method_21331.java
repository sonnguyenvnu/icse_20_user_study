@Override public void onBind(){
  final Photo photo=this.project.photo();
  if (photo != null) {
    this.profileCardImageView.setVisibility(View.VISIBLE);
    Picasso.with(context()).load(photo.med()).placeholder(this.grayGradientDrawable).into(this.profileCardImageView);
  }
 else {
    this.profileCardImageView.setVisibility(View.INVISIBLE);
  }
  this.profileCardNameTextView.setText(this.project.name());
  this.percentageFundedProgressBar.setProgress(ProgressBarUtils.progress(this.project.percentageFunded()));
  setProjectStateView();
}
