private void load(Context context,Uri uri){
  try (InputStream in=context.getContentResolver().openInputStream(uri)){
    if (in == null)     return;
    BitmapFactory.Options options=new BitmapFactory.Options();
    options.inJustDecodeBounds=true;
    BitmapFactory.decodeStream(in,null,options);
    width=options.outWidth;
    height=options.outHeight;
  }
 catch (  FileNotFoundException e) {
    Log.e(TAG,"loadMetadata -> file not found",e);
    return;
  }
catch (  IOException e) {
    Log.e(TAG,"loadMetadata -> IOException",e);
    return;
  }
  try (InputStream in=context.getContentResolver().openInputStream(uri)){
    if (in == null)     return;
    Metadata metadata=ImageMetadataReader.readMetadata(in);
    handleDirectoryBase(metadata.getFirstDirectoryOfType(ExifIFD0Directory.class));
    ExifSubIFDDirectory dir=metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
    if (dir != null) {
      dateOriginal=dir.getDateOriginal(TimeZone.getDefault());
      handleDirectoryBase(dir);
    }
    GpsDirectory d=metadata.getFirstDirectoryOfType(GpsDirectory.class);
    if (d != null)     location=d.getGeoLocation();
  }
 catch (  FileNotFoundException e) {
    Log.e(TAG,"loadMetadata -> file not found",e);
  }
catch (  IOException e) {
    Log.e(TAG,"loadMetadata -> IOException",e);
  }
catch (  ImageProcessingException e) {
    Log.e(TAG,"loadMetadata -> file type not supported",e);
  }
}
