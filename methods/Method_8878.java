private void updateSelectedBlurType(){
  if (blurType == 0) {
    Drawable drawable=blurOffButton.getContext().getResources().getDrawable(R.drawable.blur_off).mutate();
    drawable.setColorFilter(new PorterDuffColorFilter(0xff51bdf3,PorterDuff.Mode.MULTIPLY));
    blurOffButton.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
    blurOffButton.setTextColor(0xff51bdf3);
    blurRadialButton.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.blur_radial,0,0);
    blurRadialButton.setTextColor(0xffffffff);
    blurLinearButton.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.blur_linear,0,0);
    blurLinearButton.setTextColor(0xffffffff);
  }
 else   if (blurType == 1) {
    blurOffButton.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.blur_off,0,0);
    blurOffButton.setTextColor(0xffffffff);
    Drawable drawable=blurOffButton.getContext().getResources().getDrawable(R.drawable.blur_radial).mutate();
    drawable.setColorFilter(new PorterDuffColorFilter(0xff51bdf3,PorterDuff.Mode.MULTIPLY));
    blurRadialButton.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
    blurRadialButton.setTextColor(0xff51bdf3);
    blurLinearButton.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.blur_linear,0,0);
    blurLinearButton.setTextColor(0xffffffff);
  }
 else   if (blurType == 2) {
    blurOffButton.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.blur_off,0,0);
    blurOffButton.setTextColor(0xffffffff);
    blurRadialButton.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.blur_radial,0,0);
    blurRadialButton.setTextColor(0xffffffff);
    Drawable drawable=blurOffButton.getContext().getResources().getDrawable(R.drawable.blur_linear).mutate();
    drawable.setColorFilter(new PorterDuffColorFilter(0xff51bdf3,PorterDuff.Mode.MULTIPLY));
    blurLinearButton.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
    blurLinearButton.setTextColor(0xff51bdf3);
  }
}
