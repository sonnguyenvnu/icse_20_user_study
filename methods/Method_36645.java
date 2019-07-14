private void initUI(Context context,int size){
  setOrientation(VERTICAL);
  setGravity(Gravity.CENTER_HORIZONTAL);
  setBackgroundColor(Color.WHITE);
  icon=new ImageView(context);
  icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
  LayoutParams iconLp=new LayoutParams(size,size);
  iconLp.topMargin=Style.dp2px(8);
  addView(icon,iconLp);
  titleTextView=new TextView(context);
  titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);
  LayoutParams titleLp=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
  titleLp.topMargin=Style.dp2px(4.0);
  addView(titleTextView,titleLp);
  Space space=new Space(context);
  LayoutParams spaceLp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,Style.dp2px(8));
  addView(space,spaceLp);
}
