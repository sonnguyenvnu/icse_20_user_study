private static Font find(String[] fnames,int style,int size){
  for (  String name : fnames) {
    if (exist(name)) {
      return new Font(name,style,size);
    }
  }
  return null;
}
