@OnBoundsDefined static void onBoundDefined(ComponentContext c,ComponentLayout layout,@FromPrepare final FrescoState frescoState){
  frescoState.setTargetWidthPx(layout.getWidth());
  frescoState.setTargetHeightPx(layout.getHeight());
}
