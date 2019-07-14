void setTheme(Context context,boolean dark){
  Resources res=context.getResources();
  if (dark) {
    mCircleColor=res.getColor(R.color.dark_gray);
    mDotColor=res.getColor(R.color.light_gray);
  }
 else {
    mCircleColor=res.getColor(R.color.white);
    mDotColor=res.getColor(R.color.numbers_text_color);
  }
}
