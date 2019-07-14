/** 
 * dp?px
 * @param dpValue dp?
 * @return px?
 */
public static int dp2px(float dpValue){
  final float scale=RxTool.getContext().getResources().getDisplayMetrics().density;
  return (int)(dpValue * scale + 0.5f);
}
