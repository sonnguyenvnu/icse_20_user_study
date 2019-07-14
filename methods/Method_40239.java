public static String relPath(String path1,String path2){
  String a=unifyPath(path1);
  String b=unifyPath(path2);
  String[] as=a.split("[/\\\\]");
  String[] bs=b.split("[/\\\\]");
  int i;
  for (i=0; i < Math.min(as.length,bs.length); i++) {
    if (!as[i].equals(bs[i])) {
      break;
    }
  }
  int ups=as.length - i - 1;
  File res=null;
  for (int x=0; x < ups; x++) {
    res=new File(res,"..");
  }
  for (int y=i; y < bs.length; y++) {
    res=new File(res,bs[y]);
  }
  if (res == null) {
    return null;
  }
 else {
    return res.getPath();
  }
}
