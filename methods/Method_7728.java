private boolean checkTag(String key,View view){
  if (key == null || view == null) {
    return false;
  }
  Object viewTag=view.getTag();
  if (viewTag instanceof String) {
    return ((String)viewTag).contains(key);
  }
  return false;
}
