private void setViewColor(View view,int color){
  if (view.getBackground() instanceof GradientDrawable) {
    GradientDrawable drawable=(GradientDrawable)view.getBackground().mutate();
    drawable.setColor(color);
  }
}
