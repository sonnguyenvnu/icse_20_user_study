File compress() throws IOException {
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.inSampleSize=computeSize();
  Bitmap tagBitmap=BitmapFactory.decodeFile(srcImg,options);
  ByteArrayOutputStream stream=new ByteArrayOutputStream();
  tagBitmap=rotatingImage(tagBitmap);
  tagBitmap.compress(Bitmap.CompressFormat.JPEG,35,stream);
  tagBitmap.recycle();
  FileOutputStream fos=new FileOutputStream(tagImg);
  fos.write(stream.toByteArray());
  fos.flush();
  fos.close();
  stream.close();
  return tagImg;
}
