public static void applyXYToDrawableForAnimation(Drawable drawable,int x,int y){
  final Rect bounds=drawable.getBounds();
  drawable.setBounds(x,y,bounds.width() + x,bounds.height() + y);
}
