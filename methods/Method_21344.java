public void onBind(){
  final Photo photo=this.project.photo();
  if (photo != null) {
    this.projectContextImageView.setVisibility(View.VISIBLE);
    Picasso.with(this.context).load(photo.full()).into(this.projectContextImageView);
  }
 else {
    this.projectContextImageView.setVisibility(View.INVISIBLE);
  }
  this.projectNameTextView.setText(this.project.name());
  this.creatorNameTextView.setText(this.ksString.format(this.projectCreatorByCreatorString,"creator_name",this.project.creator().name()));
}
