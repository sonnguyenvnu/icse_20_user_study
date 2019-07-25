private void init(Activity context,PeekViewOptions options,@NonNull View content,@Nullable OnPeek callbacks){
  this.options=options;
  this.callbacks=callbacks;
  FINGER_SIZE=DensityUtils.toPx(context,FINGER_SIZE_DP);
  androidContentView=(FrameLayout)context.findViewById(android.R.id.content).getRootView();
  Display display=context.getWindowManager().getDefaultDisplay();
  Point size=new Point();
  display.getSize(size);
  screenHeight=size.y;
  screenWidth=size.x;
  this.content=content;
  contentParams=content.getLayoutParams();
  if (options.getAbsoluteHeight() != 0) {
    setHeight(DensityUtils.toPx(context,options.getAbsoluteHeight()));
  }
 else {
    setHeightByPercent(options.getHeightPercent());
  }
  if (options.getAbsoluteWidth() != 0) {
    setWidth(DensityUtils.toPx(context,options.getAbsoluteWidth()));
  }
 else {
    setWidthByPercent(options.getWidthPercent());
  }
  if (callbacks != null) {
    callbacks.onInflated(this,content);
  }
  dim=new View(context);
  dim.setBackgroundColor(Color.BLACK);
  dim.setAlpha(options.getBackgroundDim());
  LayoutParams dimParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
  dim.setLayoutParams(dimParams);
  if (options.shouldBlurBackground()) {
    try {
      Blurry.with(context).radius(2).sampling(5).animate().color(options.getBlurOverlayColor()).onto((ViewGroup)androidContentView.getRootView());
      dim.setAlpha(0f);
    }
 catch (    Exception ignored) {
    }
  }
  addView(dim);
  addView(content);
}
