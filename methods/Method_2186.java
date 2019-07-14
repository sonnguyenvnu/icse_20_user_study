@DoNotStrip public static @Nullable Bitmap hookDecodeFile(String pathName,BitmapFactory.Options opts){
  Bitmap bitmap=null;
  try (InputStream stream=new FileInputStream(pathName)){
    bitmap=hookDecodeStream(stream,null,opts);
  }
 catch (  Exception e) {
  }
  return bitmap;
}
