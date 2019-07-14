private static void measureViewWithFixedWidth(TextView tipView,int width){
  tipView.measure(View.MeasureSpec.makeMeasureSpec(width,View.MeasureSpec.EXACTLY),ViewGroup.LayoutParams.WRAP_CONTENT);
}
