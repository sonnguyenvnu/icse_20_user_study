public static void applySizeToDrawableForAnimation(Drawable drawable,int width,int height){
  final Rect bounds=drawable.getBounds();
  drawable.setBounds(bounds.left,bounds.top,bounds.left + width,bounds.top + height);
  if (drawable instanceof MatrixDrawable) {
    ((MatrixDrawable)drawable).bind(width,height);
  }
}
