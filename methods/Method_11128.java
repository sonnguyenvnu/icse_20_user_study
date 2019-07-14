private static void setTipBackground(View tipView,int drawableRes,int color){
  Drawable paintedDrawable=getTintedDrawable(tipView.getContext(),drawableRes,color);
  setViewBackground(tipView,paintedDrawable);
}
