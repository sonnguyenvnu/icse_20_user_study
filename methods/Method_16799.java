private String[] getAllowFiles(Object fileExt){
  String[] exts=null;
  String ext=null;
  if (fileExt == null) {
    return new String[0];
  }
  exts=(String[])fileExt;
  for (int i=0, len=exts.length; i < len; i++) {
    ext=exts[i];
    exts[i]=ext.replace(".","");
  }
  return exts;
}
