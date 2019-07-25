@Override public void init(String... args){
  for (int i=0; args != null && i < args.length; i++) {
    String a=args[i];
    if ("-ftpPort".equals(a)) {
      port=Integer.decode(args[++i]);
    }
 else     if ("-ftpDir".equals(a)) {
      root=FileUtils.toRealPath(args[++i]);
    }
 else     if ("-ftpRead".equals(a)) {
      readUserName=args[++i];
    }
 else     if ("-ftpWrite".equals(a)) {
      writeUserName=args[++i];
    }
 else     if ("-ftpWritePassword".equals(a)) {
      writePassword=args[++i];
    }
 else     if ("-trace".equals(a)) {
      trace=true;
    }
 else     if ("-ftpTask".equals(a)) {
      allowTask=true;
    }
  }
}
