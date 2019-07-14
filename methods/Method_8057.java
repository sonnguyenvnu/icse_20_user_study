public void setColors(String icon,String text){
  textView.setTextColor(Theme.getColor(text));
  textView.setTag(text);
  imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(icon),PorterDuff.Mode.MULTIPLY));
  imageView.setTag(icon);
}
