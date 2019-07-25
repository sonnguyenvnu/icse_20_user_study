@Override public void init(String... args){
  port=DEFAULT_PORT;
  for (int i=0; args != null && i < args.length; i++) {
    String a=args[i];
    if (Tool.isOption(a,"-trace")) {
      trace=true;
    }
 else     if (Tool.isOption(a,"-pgPort")) {
      port=Integer.decode(args[++i]);
      portIsSet=true;
    }
 else     if (Tool.isOption(a,"-baseDir")) {
      baseDir=args[++i];
    }
 else     if (Tool.isOption(a,"-pgAllowOthers")) {
      allowOthers=true;
    }
 else     if (Tool.isOption(a,"-pgDaemon")) {
      isDaemon=true;
    }
 else     if (Tool.isOption(a,"-ifExists")) {
      ifExists=true;
    }
 else     if (Tool.isOption(a,"-ifNotExists")) {
      ifExists=false;
    }
 else     if (Tool.isOption(a,"-key")) {
      key=args[++i];
      keyDatabase=args[++i];
    }
  }
  org.h2.Driver.load();
}
