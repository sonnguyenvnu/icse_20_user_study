/** 
 * ??View? top Padding ????????????
 */
public static void initPaddingTopDiffBar(View view){
  view.setPadding(view.getPaddingStart(),view.getPaddingTop() + DensityUtil.getStatusHeight(view.getContext()),view.getPaddingEnd(),view.getPaddingBottom());
}
