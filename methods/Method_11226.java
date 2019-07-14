public Bitmap into(ImageView imageView){
  Bitmap bitmap=createBitmap();
  if (imageView != null) {
    imageView.setImageBitmap(bitmap);
  }
  return bitmap;
}
