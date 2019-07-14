protected View getColoredView(int bgColor,String msg){
  TextView tv=new TextView(this);
  tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
  tv.setText(msg);
  tv.setBackgroundColor(bgColor);
  tv.setPadding(10,10,10,10);
  tv.setTextColor(getContrastColor(bgColor));
  return tv;
}
