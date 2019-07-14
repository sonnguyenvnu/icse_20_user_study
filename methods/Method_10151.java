@Override public void requestInitialized(final ServletRequestEvent servletRequestEvent){
  Locales.setLocale(Latkes.getLocale());
  Sessions.setTemplateDir(Symphonys.SKIN_DIR_NAME);
  Sessions.setMobile(false);
  Sessions.setAvatarViewMode(UserExt.USER_AVATAR_VIEW_MODE_C_ORIGINAL);
  final HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequestEvent.getServletRequest();
  final String userAgentStr=httpServletRequest.getHeader(Common.USER_AGENT);
  final UserAgent userAgent=UserAgent.parseUserAgentString(userAgentStr);
  BrowserType browserType=userAgent.getBrowser().getBrowserType();
  if (StringUtils.containsIgnoreCase(userAgentStr,"mobile") || StringUtils.containsIgnoreCase(userAgentStr,"MQQBrowser") || StringUtils.containsIgnoreCase(userAgentStr,"iphone") || StringUtils.containsIgnoreCase(userAgentStr,"MicroMessenger") || StringUtils.containsIgnoreCase(userAgentStr,"CFNetwork") || StringUtils.containsIgnoreCase(userAgentStr,"Android")) {
    browserType=BrowserType.MOBILE_BROWSER;
  }
 else   if (StringUtils.containsIgnoreCase(userAgentStr,"Iframely") || StringUtils.containsIgnoreCase(userAgentStr,"Google") || StringUtils.containsIgnoreCase(userAgentStr,"BUbiNG") || StringUtils.containsIgnoreCase(userAgentStr,"ltx71")) {
    browserType=BrowserType.ROBOT;
  }
 else   if (BrowserType.UNKNOWN == browserType) {
    if (!StringUtils.containsIgnoreCase(userAgentStr,"Java") && !StringUtils.containsIgnoreCase(userAgentStr,"MetaURI") && !StringUtils.containsIgnoreCase(userAgentStr,"Feed") && !StringUtils.containsIgnoreCase(userAgentStr,"okhttp") && !StringUtils.containsIgnoreCase(userAgentStr,"Sym")) {
      LOGGER.log(Level.WARN,"Unknown client [UA=" + userAgentStr + ", remoteAddr=" + Requests.getRemoteAddr(httpServletRequest) + ", URI=" + httpServletRequest.getRequestURI() + "]");
    }
  }
  if (BrowserType.ROBOT == browserType) {
    LOGGER.log(Level.DEBUG,"Request made from a search engine [User-Agent={0}]",httpServletRequest.getHeader(Common.USER_AGENT));
    Sessions.setBot(true);
    return;
  }
  Sessions.setBot(false);
  if (StaticResources.isStatic(httpServletRequest)) {
    return;
  }
  Stopwatchs.start("Request initialized [" + httpServletRequest.getRequestURI() + "]");
  Sessions.setMobile(BrowserType.MOBILE_BROWSER == browserType);
  resolveSkinDir(httpServletRequest);
}
