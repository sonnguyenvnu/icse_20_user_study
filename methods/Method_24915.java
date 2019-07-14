protected StringList getMachineParams(){
  StringList params=new StringList();
  String options=Preferences.get("run.options");
  if (options.length() > 0) {
    String pieces[]=PApplet.split(options,' ');
    for (int i=0; i < pieces.length; i++) {
      String p=pieces[i].trim();
      if (p.length() > 0) {
        params.append(p);
      }
    }
  }
  if (Preferences.getBoolean("run.options.memory")) {
    params.append("-Xms" + Preferences.get("run.options.memory.initial") + "m");
    params.append("-Xmx" + Preferences.get("run.options.memory.maximum") + "m");
  }
  params.append("-Djna.nosys=true");
  try {
    String extPath=new File(Platform.getJavaHome(),"lib/ext").getCanonicalPath();
    params.append("-Djava.ext.dirs=" + extPath);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  if (Platform.isMacOS()) {
    params.append("-Xdock:name=" + build.getSketchClassName());
  }
  params.append("-Djava.library.path=" + build.getJavaLibraryPath() + File.pathSeparator + System.getProperty("java.library.path"));
  params.append("-cp");
  params.append(build.getClassPath());
  params.append("-ea");
  return params;
}
