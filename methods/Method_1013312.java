public File resolve(final String filename,final boolean isModule){
  final String name=new File(filename).getName();
  File file=fileCache.get(name);
  if (file == null || !file.exists()) {
    byte[] bs=new byte[0];
    try {
      bs=server.getFile(name);
    }
 catch (    RemoteException e) {
      e.printStackTrace();
    }
    file=writeToNewTempFile(name,bs);
    fileCache.put(name,file);
  }
  return file;
}
