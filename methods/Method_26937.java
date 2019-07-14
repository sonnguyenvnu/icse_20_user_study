public static void animateTransform(@NonNull ImageView imageView,@Nullable Matrix matrix){
  Drawable drawable=imageView.getDrawable();
  if (drawable == null) {
    return;
  }
  if (matrix == null || drawable.getIntrinsicWidth() == -1 || drawable.getIntrinsicHeight() == -1) {
    drawable.setBounds(0,0,imageView.getWidth(),imageView.getHeight());
  }
 else {
    drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
    Matrix drawMatrix=imageView.getImageMatrix();
    if (drawMatrix.isIdentity()) {
      drawMatrix=new Matrix();
      ReflectionUtils.setFieldValue(imageView,FIELD_DRAW_MATRIX,drawMatrix);
    }
    drawMatrix.set(matrix);
  }
  imageView.invalidate();
}
