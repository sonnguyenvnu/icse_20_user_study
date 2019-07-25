@Override public boolean test(String url){
  if (url.contains("get_video_info") && mSelectedFormat != VideoFormat._Auto_) {
    return true;
  }
  return false;
}
