public void print(String path){
  List<String> list=getFileList(path);
  if (list == null) {
    return;
  }
  int length=list.size();
  for (int i=0; i < length; i++) {
    String result="";
    String thePath=getFormatPath(getString(list.get(i)));
    File file=new File(thePath);
    if (file.isDirectory()) {
      String fileName=file.getName();
      if (fileName.indexOf("_") < 0) {
        print(thePath);
        continue;
      }
      String[] filenames=fileName.split("_");
      String filename1=filenames[0];
      String filename2=filenames[1];
      result=filename1 + "," + filename2 + ",file:/" + path + "/" + fileName + "//,4,false";
      org.jeecgframework.core.util.LogUtil.info(result);
    }
 else     if (file.isFile()) {
      String fileName=file.getName();
      if (fileName.indexOf("_") < 0) {
        continue;
      }
      int last=fileName.lastIndexOf("_");
      String filename1=fileName.substring(0,last);
      String filename2=fileName.substring(last + 1,fileName.length() - 4);
      result=filename1 + "," + filename2 + ",file:/" + path + "/" + fileName + ",4,false";
      org.jeecgframework.core.util.LogUtil.info(result);
    }
  }
}
