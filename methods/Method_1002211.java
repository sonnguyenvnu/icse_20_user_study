@Override public List<MediaItem> parse(){
  if (mMpdContent == null) {
    return new ArrayList<>();
  }
  List<MediaItem> result=null;
  try {
    result=readDashMPD();
  }
 catch (  Exception e) {
    throw new IllegalStateException(e);
  }
  return result;
}
