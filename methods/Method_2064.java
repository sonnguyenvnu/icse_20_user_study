@SuppressWarnings("ResourceType") private void initColors(){
  final TypedArray attrs=getActivity().getTheme().obtainStyledAttributes(R.style.AppTheme,new int[]{R.attr.colorPrimary,android.R.attr.windowBackground});
  try {
    mColorPrimary=attrs.getColor(0,Color.BLACK);
    mWindowBackgroundColor=attrs.getColor(1,Color.BLUE);
  }
  finally {
    attrs.recycle();
  }
}
