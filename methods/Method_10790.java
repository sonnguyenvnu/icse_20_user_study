/** 
 * px?dp
 * @param pxValue px?
 * @return dp?
 */
public static int px2dp(float pxValue){
  final float scale=RxTool.getContext().getResources().getDisplayMetrics().density;
  return (int)(pxValue / scale + 0.5f);
}
