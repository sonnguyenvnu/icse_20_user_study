public void setColors(int text,int icon){
  textView.setTextColor(text);
  imageView.setColorFilter(new PorterDuffColorFilter(icon,PorterDuff.Mode.MULTIPLY));
}
