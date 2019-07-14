private String prefixTag(String tag){
  if (mApplicationTag != null) {
    return mApplicationTag + ":" + tag;
  }
 else {
    return tag;
  }
}
