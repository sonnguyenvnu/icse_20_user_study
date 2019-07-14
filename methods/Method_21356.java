private void setProjectSocialClickListener(){
  this.projectSocialViewGroup.setBackground(this.clickIndicatorLightMaskedDrawable);
  this.projectSocialViewGroup.setOnClickListener(__ -> this.viewModel.inputs.projectSocialViewGroupClicked());
}
