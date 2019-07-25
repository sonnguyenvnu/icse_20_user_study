public UrlMapping load(NutConfig config){
  if (log.isInfoEnabled()) {
    log.infof("Nutz Version : %s ",Nutz.version());
    log.infof("Nutz.Mvc[%s] is initializing ...",config.getAppName());
  }
  if (log.isDebugEnabled()) {
    Properties sys=System.getProperties();
    log.debug("Web Container Information:");
    log.debugf(" - Default Charset : %s",Encoding.defaultEncoding());
    log.debugf(" - Current . path  : %s",new File(".").getAbsolutePath());
    log.debugf(" - Java Version    : %s",sys.get("java.version"));
    log.debugf(" - File separator  : %s",sys.get("file.separator"));
    log.debugf(" - Timezone        : %s",sys.get("user.timezone"));
    log.debugf(" - OS              : %s %s",sys.get("os.name"),sys.get("os.arch"));
    log.debugf(" - ServerInfo      : %s",config.getServletContext().getServerInfo());
    log.debugf(" - Servlet API     : %d.%d",config.getServletContext().getMajorVersion(),config.getServletContext().getMinorVersion());
    if (config.getServletContext().getMajorVersion() > 2 || config.getServletContext().getMinorVersion() > 4)     log.debugf(" - ContextPath     : %s",config.getServletContext().getContextPath());
    log.debugf(" - context.tempdir : %s",config.getAttribute("javax.servlet.context.tempdir"));
    log.debugf(" - MainModule      : %s",config.getMainModule().getName());
  }
  UrlMapping mapping;
  Ioc ioc=null;
  Stopwatch sw=Stopwatch.begin();
  try {
    Class<?> mainModule=config.getMainModule();
    createContext(config);
    ioc=createIoc(config,mainModule);
    mapping=evalUrlMapping(config,mainModule,ioc);
    evalLocalization(config,mainModule);
    createSessionProvider(config,mainModule);
    evalSetup(config,mainModule);
  }
 catch (  Exception e) {
    if (log.isErrorEnabled())     log.error("Error happend during start serivce!",e);
    if (ioc != null) {
      log.error("try to depose ioc");
      try {
        ioc.depose();
      }
 catch (      Throwable e2) {
        log.error("error when depose ioc",e);
      }
    }
    throw Lang.wrapThrow(e,LoadingException.class);
  }
  sw.stop();
  if (log.isInfoEnabled())   log.infof("Nutz.Mvc[%s] is up in %sms",config.getAppName(),sw.getDuration());
  return mapping;
}
