private void storeImage(Bitmap image,File pictureFile,Bitmap.CompressFormat compressionFormat,int quality) throws IOException {
  FileOutputStream fos=null;
  try {
    fos=new FileOutputStream(pictureFile);
    image.compress(compressionFormat,quality,fos);
  }
 catch (  FileNotFoundException e) {
    throw new IOException(e.getMessage());
  }
 finally {
    if (fos != null) {
      fos.close();
    }
  }
}
