@Override public void init(String... args){
  for (int i=0; args != null && i < args.length; i++) {
    if ("-properties".equals(args[i])) {
      serverPropertiesDir=args[++i];
    }
  }
  Properties prop=loadProperties();
  port=SortedProperties.getIntProperty(prop,"webPort",Constants.DEFAULT_HTTP_PORT);
  ssl=SortedProperties.getBooleanProperty(prop,"webSSL",false);
  allowOthers=SortedProperties.getBooleanProperty(prop,"webAllowOthers",false);
  setAdminPassword(SortedProperties.getStringProperty(prop,"webAdminPassword",null));
  commandHistoryString=prop.getProperty(COMMAND_HISTORY);
  for (int i=0; args != null && i < args.length; i++) {
    String a=args[i];
    if (Tool.isOption(a,"-webPort")) {
      port=Integer.decode(args[++i]);
    }
 else     if (Tool.isOption(a,"-webSSL")) {
      ssl=true;
    }
 else     if (Tool.isOption(a,"-webAllowOthers")) {
      allowOthers=true;
    }
 else     if (Tool.isOption(a,"-webDaemon")) {
      isDaemon=true;
    }
 else     if (Tool.isOption(a,"-baseDir")) {
      String baseDir=args[++i];
      SysProperties.setBaseDir(baseDir);
    }
 else     if (Tool.isOption(a,"-ifExists")) {
      ifExists=true;
    }
 else     if (Tool.isOption(a,"-ifNotExists")) {
      ifExists=false;
    }
 else     if (Tool.isOption(a,"-webAdminPassword")) {
      setAdminPassword(args[++i]);
    }
 else     if (Tool.isOption(a,"-properties")) {
      i++;
    }
 else     if (Tool.isOption(a,"-trace")) {
      trace=true;
    }
  }
  for (  String[] lang : LANGUAGES) {
    languages.add(lang[0]);
  }
  if (allowOthers) {
    key=null;
  }
  updateURL();
}
