private TextureView createTextureView(boolean add){
  if (parentLayout == null) {
    return null;
  }
  if (roundVideoContainer == null) {
    if (Build.VERSION.SDK_INT >= 21) {
      roundVideoContainer=new FrameLayout(getParentActivity()){
        @Override public void setTranslationY(        float translationY){
          super.setTranslationY(translationY);
          contentView.invalidate();
        }
      }
;
      roundVideoContainer.setOutlineProvider(new ViewOutlineProvider(){
        @TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override public void getOutline(        View view,        Outline outline){
          outline.setOval(0,0,AndroidUtilities.roundMessageSize,AndroidUtilities.roundMessageSize);
        }
      }
);
      roundVideoContainer.setClipToOutline(true);
    }
 else {
      roundVideoContainer=new FrameLayout(getParentActivity()){
        @Override protected void onSizeChanged(        int w,        int h,        int oldw,        int oldh){
          super.onSizeChanged(w,h,oldw,oldh);
          aspectPath.reset();
          aspectPath.addCircle(w / 2,h / 2,w / 2,Path.Direction.CW);
          aspectPath.toggleInverseFillType();
        }
        @Override public void setTranslationY(        float translationY){
          super.setTranslationY(translationY);
          contentView.invalidate();
        }
        @Override public void setVisibility(        int visibility){
          super.setVisibility(visibility);
          if (visibility == VISIBLE) {
            setLayerType(View.LAYER_TYPE_HARDWARE,null);
          }
        }
        @Override protected void dispatchDraw(        Canvas canvas){
          super.dispatchDraw(canvas);
          canvas.drawPath(aspectPath,aspectPaint);
        }
      }
;
      aspectPath=new Path();
      aspectPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
      aspectPaint.setColor(0xff000000);
      aspectPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }
    roundVideoContainer.setWillNotDraw(false);
    roundVideoContainer.setVisibility(View.INVISIBLE);
    aspectRatioFrameLayout=new AspectRatioFrameLayout(getParentActivity());
    aspectRatioFrameLayout.setBackgroundColor(0);
    if (add) {
      roundVideoContainer.addView(aspectRatioFrameLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
    }
    videoTextureView=new TextureView(getParentActivity());
    videoTextureView.setOpaque(false);
    aspectRatioFrameLayout.addView(videoTextureView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  }
  if (roundVideoContainer.getParent() == null) {
    contentView.addView(roundVideoContainer,1,new FrameLayout.LayoutParams(AndroidUtilities.roundMessageSize,AndroidUtilities.roundMessageSize));
  }
  roundVideoContainer.setVisibility(View.INVISIBLE);
  aspectRatioFrameLayout.setDrawingReady(false);
  return videoTextureView;
}
