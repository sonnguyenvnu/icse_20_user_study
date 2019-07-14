private String parseFileName(String url){
  int index=-1;
  if (url.lastIndexOf('/') != -1) {
    index=url.lastIndexOf('/');
  }
 else {
    index=url.lastIndexOf(File.separator);
  }
  AssertUtils.isTrue(index != -1,"Format of biz file location is error.");
  return url.substring(index + 1);
}
