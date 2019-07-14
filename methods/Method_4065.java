private int updateSpecWithExtra(int spec,int startInset,int endInset){
  if (startInset == 0 && endInset == 0) {
    return spec;
  }
  final int mode=View.MeasureSpec.getMode(spec);
  if (mode == View.MeasureSpec.AT_MOST || mode == View.MeasureSpec.EXACTLY) {
    return View.MeasureSpec.makeMeasureSpec(Math.max(0,View.MeasureSpec.getSize(spec) - startInset - endInset),mode);
  }
  return spec;
}
