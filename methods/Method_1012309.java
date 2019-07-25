private void init(){
  if (!(getLayoutParams() instanceof LayoutParams)) {
    LayoutParams layoutParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,Gravity.RIGHT | Gravity.TOP);
    setLayoutParams(layoutParams);
  }
  setTextColor(Color.WHITE);
  setTypeface(Typeface.DEFAULT_BOLD);
  setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
  setPadding(dip2Px(5),dip2Px(1),dip2Px(5),dip2Px(1));
  setBackground(9,Color.parseColor("#d3321b"));
  setGravity(Gravity.CENTER);
  setHideOnNull(true);
}
