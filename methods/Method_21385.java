private void showBackedProject(final @NonNull String projectName){
  this.backedProjectTextView.setText(Html.fromHtml(this.ksString.format(this.youJustBackedString,"project_name",projectName)));
}
