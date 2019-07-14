private void resizeProjectImage(final @Nullable String avatarUrl){
  final int targetImageWidth=(int)(getScreenWidthDp(context()) * getScreenDensity(context()) - this.gridNew4Dimen);
  final int targetImageHeight=ProjectUtils.photoHeightFromWidthRatio(targetImageWidth);
  this.photoImageView.setMaxHeight(targetImageHeight);
  Picasso.with(this.context()).load(avatarUrl).resize(targetImageWidth,targetImageHeight).centerCrop().placeholder(this.grayGradientDrawable).into(this.photoImageView);
}
