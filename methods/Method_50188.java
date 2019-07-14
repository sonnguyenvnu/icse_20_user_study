public String getThumbnailBigPath(){
  if (new File(thumbnailBigPath).exists()) {
    return thumbnailBigPath;
  }
  return "";
}
