@Override public void remove(String key){
  if (mUrl == null) {
    return;
  }
  final String template="\\/%s\\/[^\\/]*";
  mUrl=mUrl.replaceAll(String.format(template,key),"");
}
