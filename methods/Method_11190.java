protected GradientDrawable createGradientDrawable(int color){
  GradientDrawable gradientDrawable=new GradientDrawable();
  gradientDrawable.setShape(GradientDrawable.RECTANGLE);
  gradientDrawable.setColor(color);
  return gradientDrawable;
}
