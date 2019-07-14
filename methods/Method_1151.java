@Override public Drawable setCurrent(Drawable newDelegate){
  final Drawable previousDelegate=super.setCurrent(newDelegate);
  configureBounds();
  return previousDelegate;
}
