/** 
 * Resolve skin (template) for the specified HTTP servlet request.
 * @param request the specified HTTP servlet request
 */
private void resolveSkinDir(final HttpServletRequest request){
  Stopwatchs.start("Resolve skin");
  final String templateDirName=Sessions.isMobile() ? "mobile" : "classic";
  Sessions.setTemplateDir(templateDirName);
  final HttpSession httpSession=request.getSession();
  httpSession.setAttribute(Keys.TEMAPLTE_DIR_NAME,templateDirName);
  try {
    final UserQueryService userQueryService=beanManager.getReference(UserQueryService.class);
    final OptionRepository optionRepository=beanManager.getReference(OptionRepository.class);
    final JSONObject optionLang=optionRepository.get(Option.ID_C_MISC_LANGUAGE);
    final String optionLangValue=optionLang.optString(Option.OPTION_VALUE);
    if ("0".equals(optionLangValue)) {
      Locales.setLocale(request.getLocale());
    }
 else {
      Locales.setLocale(Locales.getLocale(optionLangValue));
    }
    JSONObject user=userQueryService.getCurrentUser(request);
    if (null == user) {
      return;
    }
    final String skin=Sessions.isMobile() ? user.optString(UserExt.USER_MOBILE_SKIN) : user.optString(UserExt.USER_SKIN);
    httpSession.setAttribute(Keys.TEMAPLTE_DIR_NAME,skin);
    Sessions.setTemplateDir(skin);
    Sessions.setAvatarViewMode(user.optInt(UserExt.USER_AVATAR_VIEW_MODE));
    Sessions.setUser(user);
    Sessions.setLoggedIn(true);
    final Locale locale=Locales.getLocale(user.optString(UserExt.USER_LANGUAGE));
    Locales.setLocale(locale);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Resolves skin failed",e);
  }
 finally {
    Stopwatchs.end();
  }
}
