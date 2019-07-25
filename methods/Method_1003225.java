private String index(){
  String[][] languageArray=WebServer.LANGUAGES;
  String language=(String)attributes.get("language");
  Locale locale=session.locale;
  if (language != null) {
    if (locale == null || !StringUtils.toLowerEnglish(locale.getLanguage()).equals(language)) {
      locale=new Locale(language,"");
      server.readTranslations(session,locale.getLanguage());
      session.put("language",language);
      session.locale=locale;
    }
  }
 else {
    language=(String)session.get("language");
  }
  if (language == null) {
    language=headerLanguage;
  }
  session.put("languageCombo",getComboBox(languageArray,language));
  String[] settingNames=server.getSettingNames();
  String setting=attributes.getProperty("setting");
  if (setting == null && settingNames.length > 0) {
    setting=settingNames[0];
  }
  String combobox=getComboBox(settingNames,setting);
  session.put("settingsList",combobox);
  ConnectionInfo info=server.getSetting(setting);
  if (info == null) {
    info=new ConnectionInfo();
  }
  session.put("setting",PageParser.escapeHtmlData(setting));
  session.put("name",PageParser.escapeHtmlData(setting));
  session.put("driver",PageParser.escapeHtmlData(info.driver));
  session.put("url",PageParser.escapeHtmlData(info.url));
  session.put("user",PageParser.escapeHtmlData(info.user));
  return "index.jsp";
}
