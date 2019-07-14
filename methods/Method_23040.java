static public String[] listFiles(File folder,boolean relative,String extension){
  if (extension != null) {
    if (!extension.startsWith(".")) {
      extension="." + extension;
    }
  }
  StringList list=new StringList();
  listFilesImpl(folder,relative,extension,list);
  if (relative) {
    String[] outgoing=new String[list.size()];
    int prefixLength=folder.getAbsolutePath().length() + 1;
    for (int i=0; i < outgoing.length; i++) {
      outgoing[i]=list.get(i).substring(prefixLength);
    }
    return outgoing;
  }
  return list.array();
}
