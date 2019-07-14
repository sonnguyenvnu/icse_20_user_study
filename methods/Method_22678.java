static public void init(){
  try {
    load(Base.getLibStream(DEFAULTS_FILE));
  }
 catch (  Exception e) {
    Messages.showError(null,"Could not read default settings.\n" + "You'll need to reinstall Processing.",e);
  }
  defaults=new HashMap<>(table);
  setColor("run.window.bgcolor",SystemColor.control);
  if (Language.useInputMethod()) {
    setBoolean("editor.input_method_support",true);
  }
  preferencesFile=Base.getSettingsFile(PREFS_FILE);
  boolean firstRun=!preferencesFile.exists();
  if (!firstRun) {
    try {
      load(new FileInputStream(preferencesFile));
    }
 catch (    Exception ex) {
      Messages.showError("Error reading preferences","Error reading the preferences file. " + "Please delete (or move)\n" + preferencesFile.getAbsolutePath() + " and restart Processing.",ex);
    }
  }
  if (checkSketchbookPref() || firstRun) {
    save();
  }
  PApplet.useNativeSelect=Preferences.getBoolean("chooser.files.native");
  if (get("proxy.system").equals("true")) {
    System.setProperty("java.net.useSystemProxies","true");
  }
  handleProxy("http","http.proxyHost","http.proxyPort");
  handleProxy("https","https.proxyHost","https.proxyPort");
  handleProxy("socks","socksProxyHost","socksProxyPort");
}
