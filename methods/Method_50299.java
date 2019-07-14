/** 
 * ???????
 */
public static void createVideoThumbnail(String thumbnailSaveDir,String originalPath,int scale){
  Bitmap bitmap=ThumbnailUtils.createVideoThumbnail(originalPath,MediaStore.Video.Thumbnails.MINI_KIND);
  if (bitmap == null) {
    return;
  }
  int originalImageWidth=bitmap.getWidth();
  int originalImageHeight=bitmap.getHeight();
  int maxValue=Math.max(originalImageWidth,originalImageHeight);
  BufferedInputStream bufferedInputStream=null;
  FileOutputStream fileOutputStream=null;
  File targetFile;
  try {
    BitmapFactory.Options options=new BitmapFactory.Options();
    if (maxValue > 3000) {
      options.inSampleSize=scale * 5;
    }
 else     if (maxValue > 2000 && maxValue <= 3000) {
      options.inSampleSize=scale * 4;
    }
 else     if (maxValue > 1500 && maxValue <= 2000) {
      options.inSampleSize=(int)(scale * 2.5);
    }
 else     if (maxValue > 1000 && maxValue <= 1500) {
      options.inSampleSize=(int)(scale * 1.3);
    }
 else {
      options.inSampleSize=scale;
    }
    options.inJustDecodeBounds=false;
    bufferedInputStream=new BufferedInputStream(new FileInputStream(originalPath));
    Bitmap bm=BitmapFactory.decodeStream(bufferedInputStream,null,options);
    bufferedInputStream.close();
    bitmap.recycle();
    if (bm == null) {
      return;
    }
    bitmap=bm;
    String scaleStr=(scale == THUMBNAIL_BIG ? "big" : "small");
    String extension=FilenameUtils.getExtension(originalPath);
    File original=new File(originalPath);
    targetFile=new File(thumbnailSaveDir,scaleStr + "_" + original.getName().replace(extension,"jpg"));
    fileOutputStream=new FileOutputStream(targetFile);
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
  }
 catch (  Exception e) {
    Logger.e(e);
  }
 finally {
    if (!bitmap.isRecycled()) {
      bitmap.recycle();
    }
    IOUtils.close(bufferedInputStream);
    IOUtils.flush(fileOutputStream);
    IOUtils.close(fileOutputStream);
  }
}
