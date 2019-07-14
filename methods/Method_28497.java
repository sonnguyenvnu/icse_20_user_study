public void setEventsIcon(@DrawableRes int drawableRes){
  Drawable drawable=ContextCompat.getDrawable(getContext(),drawableRes);
  int width=drawable.getIntrinsicWidth();
  int height=drawable.getIntrinsicHeight();
  drawable.setBounds(0,0,width / 2,height / 2);
  ScaleDrawable sd=new ScaleDrawable(drawable,Gravity.CENTER,0.6f,0.6f);
  sd.setLevel(8000);
  ViewHelper.tintDrawable(drawable,ViewHelper.getTertiaryTextColor(getContext()));
  setCompoundDrawablesWithIntrinsicBounds(sd,null,null,null);
}
