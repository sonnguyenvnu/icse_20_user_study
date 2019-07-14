public void setIconColor(int color){
  imageView.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
}
