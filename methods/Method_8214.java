private TextureView createTextureView(boolean add){
  if (parentLayout == null) {
    return null;
  }
  if (videoPlayerContainer == null) {
    if (Build.VERSION.SDK_INT >= 21) {
      videoPlayerContainer=new FrameLayout(getParentActivity()){
        @Override public void setTranslationY(        float translationY){
          super.setTranslationY(translationY);
          contentView.invalidate();
        }
      }
;
      videoPlayerContainer.setOutlineProvider(new ViewOutlineProvider(){
        @TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override public void getOutline(        View view,        Outline outline){
          if (view.getTag(R.id.parent_tag) != null) {
            outline.setRoundRect(0,0,view.getMeasuredWidth(),view.getMeasuredHeight(),AndroidUtilities.dp(4));
          }
 else {
            outline.setOval(0,0,AndroidUtilities.roundMessageSize,AndroidUtilities.roundMessageSize);
          }
        }
      }
);
      videoPlayerContainer.setClipToOutline(true);
    }
 else {
      videoPlayerContainer=new FrameLayout(getParentActivity()){
        @Override protected void onSizeChanged(        int w,        int h,        int oldw,        int oldh){
          super.onSizeChanged(w,h,oldw,oldh);
          aspectPath.reset();
          if (getTag(R.id.parent_tag) != null) {
            rect.set(0,0,w,h);
            aspectPath.addRoundRect(rect,AndroidUtilities.dp(4),AndroidUtilities.dp(4),Path.Direction.CW);
          }
 else {
            aspectPath.addCircle(w / 2,h / 2,w / 2,Path.Direction.CW);
          }
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
          if (getTag() == null) {
            canvas.drawPath(aspectPath,aspectPaint);
          }
        }
      }
;
      aspectPath=new Path();
      aspectPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
      aspectPaint.setColor(0xff000000);
      aspectPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }
    videoPlayerContainer.setWillNotDraw(false);
    aspectRatioFrameLayout=new AspectRatioFrameLayout(getParentActivity());
    aspectRatioFrameLayout.setBackgroundColor(0);
    if (add) {
      videoPlayerContainer.addView(aspectRatioFrameLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
    }
    videoTextureView=new TextureView(getParentActivity());
    videoTextureView.setOpaque(false);
    aspectRatioFrameLayout.addView(videoTextureView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  }
  ViewGroup parent=(ViewGroup)videoPlayerContainer.getParent();
  if (parent != null && parent != contentView) {
    parent.removeView(videoPlayerContainer);
    parent=null;
  }
  if (parent == null) {
    contentView.addView(videoPlayerContainer,1,new FrameLayout.LayoutParams(AndroidUtilities.roundMessageSize,AndroidUtilities.roundMessageSize));
  }
  videoPlayerContainer.setTag(null);
  aspectRatioFrameLayout.setDrawingReady(false);
  return videoTextureView;
}
