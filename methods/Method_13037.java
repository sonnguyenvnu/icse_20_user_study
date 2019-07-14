@Override public Configuration load(){
  Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
  int w=(screenSize.width > Constants.DEFAULT_WIDTH) ? Constants.DEFAULT_WIDTH : screenSize.width;
  int h=(screenSize.height > Constants.DEFAULT_HEIGHT) ? Constants.DEFAULT_HEIGHT : screenSize.height;
  int x=(screenSize.width - w) / 2;
  int y=(screenSize.height - h) / 2;
  Configuration config=new Configuration();
  config.setMainWindowLocation(new Point(x,y));
  config.setMainWindowSize(new Dimension(w,h));
  config.setMainWindowMaximize(false);
  String defaultLaf=System.getProperty("swing.defaultlaf");
  config.setLookAndFeel((defaultLaf != null) ? defaultLaf : UIManager.getSystemLookAndFeelClassName());
  File recentSaveDirectory=new File(System.getProperty("user.dir"));
  config.setRecentLoadDirectory(recentSaveDirectory);
  config.setRecentSaveDirectory(recentSaveDirectory);
  if (FILE.exists()) {
    try (FileInputStream fis=new FileInputStream(FILE)){
      XMLStreamReader reader=XMLInputFactory.newInstance().createXMLStreamReader(fis);
      String name="";
      Stack<String> names=new Stack<>();
      List<File> recentFiles=new ArrayList<>();
      boolean maximize=false;
      Map<String,String> preferences=config.getPreferences();
      while (reader.hasNext()) {
switch (reader.next()) {
case XMLStreamConstants.START_ELEMENT:
          names.push(name);
        name+='/' + reader.getLocalName();
switch (name) {
case "/configuration/gui/mainWindow/location":
        x=Integer.parseInt(reader.getAttributeValue(null,"x"));
      y=Integer.parseInt(reader.getAttributeValue(null,"y"));
    break;
case "/configuration/gui/mainWindow/size":
  w=Integer.parseInt(reader.getAttributeValue(null,"w"));
h=Integer.parseInt(reader.getAttributeValue(null,"h"));
break;
}
break;
case XMLStreamConstants.END_ELEMENT:
name=names.pop();
break;
case XMLStreamConstants.CHARACTERS:
switch (name) {
case "/configuration/recentFilePaths/filePath":
File file=new File(reader.getText().trim());
if (file.exists()) {
recentFiles.add(file);
}
break;
case "/configuration/recentDirectories/loadPath":
file=new File(reader.getText().trim());
if (file.exists()) {
config.setRecentLoadDirectory(file);
}
break;
case "/configuration/recentDirectories/savePath":
file=new File(reader.getText().trim());
if (file.exists()) {
config.setRecentSaveDirectory(file);
}
break;
case "/configuration/gui/lookAndFeel":
config.setLookAndFeel(reader.getText().trim());
break;
case "/configuration/gui/mainWindow/maximize":
maximize=Boolean.parseBoolean(reader.getText().trim());
break;
default :
if (name.startsWith("/configuration/preferences/")) {
String key=name.substring("/configuration/preferences/".length());
preferences.put(key,reader.getText().trim());
}
break;
}
break;
}
}
if (recentFiles.size() > Constants.MAX_RECENT_FILES) {
recentFiles=recentFiles.subList(0,Constants.MAX_RECENT_FILES);
}
config.setRecentFiles(recentFiles);
if ((x >= 0) && (y >= 0) && (x + w < screenSize.width) && (y + h < screenSize.height)) {
config.setMainWindowLocation(new Point(x,y));
config.setMainWindowSize(new Dimension(w,h));
config.setMainWindowMaximize(maximize);
}
reader.close();
}
 catch (Exception e) {
assert ExceptionUtil.printStackTrace(e);
}
}
if (!config.getPreferences().containsKey(ERROR_BACKGROUND_COLOR)) {
config.getPreferences().put(ERROR_BACKGROUND_COLOR,"0xFF6666");
}
config.getPreferences().put(JD_CORE_VERSION,getJdCoreVersion());
return config;
}
