/** 
 * Configure the scrolling touch slop for a specific use case. Set up the RecyclerView's scrolling motion threshold based on common usages. Valid arguments are  {@link #TOUCH_SLOP_DEFAULT} and {@link #TOUCH_SLOP_PAGING}.
 * @param slopConstant One of the <code>TOUCH_SLOP_</code> constants representingthe intended usage of this RecyclerView
 */
public void setScrollingTouchSlop(int slopConstant){
  final ViewConfiguration vc=ViewConfiguration.get(getContext());
switch (slopConstant) {
default :
    Log.w(TAG,"setScrollingTouchSlop(): bad argument constant " + slopConstant + "; using default value");
case TOUCH_SLOP_DEFAULT:
  mTouchSlop=vc.getScaledTouchSlop();
break;
case TOUCH_SLOP_PAGING:
mTouchSlop=vc.getScaledPagingTouchSlop();
break;
}
}
