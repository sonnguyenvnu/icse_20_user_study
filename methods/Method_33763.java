/** 
 * ?View?top margin ????????????
 */
public static void initMarginTopDiffBar(View view){
  ViewGroup.LayoutParams params=view.getLayoutParams();
  if (params instanceof LinearLayout.LayoutParams) {
    LinearLayout.LayoutParams linearParams=(LinearLayout.LayoutParams)params;
    linearParams.topMargin+=DensityUtil.getStatusHeight(view.getContext());
  }
 else   if (params instanceof FrameLayout.LayoutParams) {
    FrameLayout.LayoutParams frameParams=(FrameLayout.LayoutParams)params;
    frameParams.topMargin+=DensityUtil.getStatusHeight(view.getContext());
  }
 else   if (params instanceof RelativeLayout.LayoutParams) {
    RelativeLayout.LayoutParams relativeParams=(RelativeLayout.LayoutParams)params;
    relativeParams.topMargin+=DensityUtil.getStatusHeight(view.getContext());
  }
 else   if (params instanceof ConstraintLayout.LayoutParams) {
    ConstraintLayout.LayoutParams constraintParams=(ConstraintLayout.LayoutParams)params;
    constraintParams.topMargin+=DensityUtil.getStatusHeight(view.getContext());
  }
  view.setLayoutParams(params);
}
