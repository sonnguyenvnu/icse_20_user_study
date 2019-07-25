@Override public void init(String... args){
  port=Constants.DEFAULT_TCP_PORT;
  for (int i=0; args != null && i < args.length; i++) {
    String a=args[i];
    if (Tool.isOption(a,"-trace")) {
      trace=true;
    }
 else     if (Tool.isOption(a,"-tcpSSL")) {
      ssl=true;
    }
 else     if (Tool.isOption(a,"-tcpPort")) {
      port=Integer.decode(args[++i]);
      portIsSet=true;
    }
 else     if (Tool.isOption(a,"-tcpPassword")) {
      managementPassword=args[++i];
    }
 else     if (Tool.isOption(a,"-baseDir")) {
      baseDir=args[++i];
    }
 else     if (Tool.isOption(a,"-key")) {
      key=args[++i];
      keyDatabase=args[++i];
    }
 else     if (Tool.isOption(a,"-tcpAllowOthers")) {
      allowOthers=true;
    }
 else     if (Tool.isOption(a,"-tcpDaemon")) {
      isDaemon=true;
    }
 else     if (Tool.isOption(a,"-ifExists")) {
      ifExists=true;
    }
 else     if (Tool.isOption(a,"-ifNotExists")) {
      ifExists=false;
    }
  }
  org.h2.Driver.load();
}
