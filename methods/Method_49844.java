private void initLayoutParameters(Configuration configuration){
  mLayoutParams=getLayoutParameters(configuration.orientation == Configuration.ORIENTATION_PORTRAIT ? LayoutParameters.HVGA_PORTRAIT : LayoutParameters.HVGA_LANDSCAPE);
  if (LOCAL_LOGV) {
    Timber.v("LayoutParameters: " + mLayoutParams.getTypeDescription() + ": " + mLayoutParams.getWidth() + "x" + mLayoutParams.getHeight());
  }
}
