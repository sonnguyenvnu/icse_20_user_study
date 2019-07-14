@Override public Result onRunJob(){
  String originalPath=mediaBean.getOriginalPath();
  File bigThumFile=MediaUtils.createThumbnailBigFileName(context,originalPath);
  File smallThumFile=MediaUtils.createThumbnailSmallFileName(context,originalPath);
  if (!bigThumFile.exists()) {
    BitmapUtils.createThumbnailBig(bigThumFile,originalPath);
  }
  if (!smallThumFile.exists()) {
    BitmapUtils.createThumbnailSmall(smallThumFile,originalPath);
  }
  Result result=Result.SUCCESS;
  result.setResultData(mediaBean);
  return result;
}
