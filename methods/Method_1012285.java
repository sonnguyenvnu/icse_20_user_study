/** 
 * ??
 * @return
 */
public TooltipView show(){
  final Context activityContext=mTooltipView.getContext();
  if (activityContext != null && activityContext instanceof Activity) {
    final ViewGroup decorView=(ViewGroup)((Activity)activityContext).getWindow().getDecorView();
    mView.postDelayed(new Runnable(){
      @Override public void run(){
        final Rect rect=new Rect();
        mView.getGlobalVisibleRect(rect);
        int[] location=new int[2];
        mView.getLocationOnScreen(location);
        rect.left=location[0];
        decorView.addView(mTooltipView,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        mTooltipView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
          @Override public boolean onPreDraw(){
            mTooltipView.setup(rect,decorView.getWidth());
            mTooltipView.getViewTreeObserver().removeOnPreDrawListener(this);
            return false;
          }
        }
);
      }
    }
,100);
  }
  return mTooltipView;
}
