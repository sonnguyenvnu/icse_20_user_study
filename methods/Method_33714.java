/** 
 * ????View?margin
 * @param view   ?????view
 * @param isDp   ??????????DP
 * @param left   ???
 * @param right  ???
 * @param top    ???
 * @param bottom ???
 * @return
 */
public static ViewGroup.LayoutParams setViewMargin(View view,boolean isDp,int left,int right,int top,int bottom){
  if (view == null) {
    return null;
  }
  int leftPx=left;
  int rightPx=right;
  int topPx=top;
  int bottomPx=bottom;
  ViewGroup.LayoutParams params=view.getLayoutParams();
  ViewGroup.MarginLayoutParams marginParams=null;
  if (params instanceof ViewGroup.MarginLayoutParams) {
    marginParams=(ViewGroup.MarginLayoutParams)params;
  }
 else {
    marginParams=new ViewGroup.MarginLayoutParams(params);
  }
  if (isDp) {
    leftPx=dip2px(left);
    rightPx=dip2px(right);
    topPx=dip2px(top);
    bottomPx=dip2px(bottom);
  }
  marginParams.setMargins(leftPx,topPx,rightPx,bottomPx);
  view.setLayoutParams(marginParams);
  view.requestLayout();
  return marginParams;
}
