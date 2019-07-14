/** 
 * Export to application without GUI. Also called by the Commander.
 */
protected boolean exportApplication(File destFolder,int exportPlatform,String exportVariant,boolean embedJava) throws IOException, SketchException {
  for (  Library library : importedLibraries) {
    if (!library.supportsArch(exportPlatform,exportVariant)) {
      String pn=PConstants.platformNames[exportPlatform];
      Messages.showWarning("Quibbles 'n Bits","The application." + pn + exportVariant + " folder will not be created\n" + "because no " + exportVariant + " version of " + library.getName() + " is available for " + pn,null);
      return true;
    }
  }
  mode.prepareExportFolder(destFolder);
  File jarFolder=new File(destFolder,"lib");
  File dotAppFolder=null;
  String jvmRuntime="";
  String jdkPath=null;
  if (exportPlatform == PConstants.MACOSX) {
    dotAppFolder=new File(destFolder,sketch.getName() + ".app");
    File contentsOrig=new File(Platform.getJavaHome(),"../../../../..");
    if (embedJava) {
      File jdkFolder=new File(Platform.getJavaHome(),"../../..");
      String jdkFolderName=jdkFolder.getCanonicalFile().getName();
      jvmRuntime="<key>JVMRuntime</key>\n    <string>" + jdkFolderName + "</string>";
      jdkPath=new File(dotAppFolder,"Contents/PlugIns/" + jdkFolderName).getAbsolutePath();
    }
    File contentsFolder=new File(dotAppFolder,"Contents");
    contentsFolder.mkdirs();
    jarFolder=new File(contentsFolder,"Java");
    File macosFolder=new File(contentsFolder,"MacOS");
    macosFolder.mkdirs();
    Util.copyFile(mode.getContentFile("application/mac-app-stub"),new File(contentsFolder,"MacOS/" + sketch.getName()));
    File pkgInfo=new File(contentsFolder,"PkgInfo");
    PrintWriter writer=PApplet.createWriter(pkgInfo);
    writer.println("APPL????");
    writer.flush();
    writer.close();
    if (embedJava) {
      Util.copyDirNative(new File(contentsOrig,"PlugIns"),new File(contentsFolder,"PlugIns"));
    }
    File resourcesFolder=new File(contentsFolder,"Resources");
    Util.copyDir(new File(contentsOrig,"Resources/en.lproj"),new File(resourcesFolder,"en.lproj"));
    Util.copyFile(mode.getContentFile("application/sketch.icns"),new File(resourcesFolder,"sketch.icns"));
  }
 else   if (exportPlatform == PConstants.LINUX) {
    if (embedJava) {
      Util.copyDirNative(Platform.getJavaHome(),new File(destFolder,"java"));
    }
  }
 else   if (exportPlatform == PConstants.WINDOWS) {
    if (embedJava) {
      Util.copyDir(Platform.getJavaHome(),new File(destFolder,"java"));
    }
  }
  if (!jarFolder.exists())   jarFolder.mkdirs();
  StringList jarList=new StringList();
  FileOutputStream zipOutputFile=new FileOutputStream(new File(jarFolder,sketch.getName() + ".jar"));
  ZipOutputStream zos=new ZipOutputStream(zipOutputFile);
  addManifest(zos);
  addClasses(zos,binFolder);
  if (sketch.hasDataFolder()) {
    if (exportPlatform == PConstants.MACOSX) {
      Util.copyDir(sketch.getDataFolder(),new File(jarFolder,"data"));
    }
 else {
      Util.copyDir(sketch.getDataFolder(),new File(destFolder,"data"));
    }
  }
  if (sketch.hasCodeFolder()) {
    String includes=Util.contentsToClassPath(sketch.getCodeFolder());
    String[] codeList=PApplet.splitTokens(includes,File.pathSeparator);
    for (int i=0; i < codeList.length; i++) {
      if (codeList[i].toLowerCase().endsWith(".jar") || codeList[i].toLowerCase().endsWith(".zip")) {
        File exportFile=new File(codeList[i]);
        String exportFilename=exportFile.getName();
        Util.copyFile(exportFile,new File(jarFolder,exportFilename));
        jarList.append(exportFilename);
      }
 else {
      }
    }
  }
  zos.flush();
  zos.close();
  jarList.append(sketch.getName() + ".jar");
  for (  Library library : importedLibraries) {
    for (    File exportFile : library.getApplicationExports(exportPlatform,exportVariant)) {
      String exportName=exportFile.getName();
      if (!exportFile.exists()) {
        System.err.println(exportFile.getName() + " is mentioned in export.txt, but it's " + "a big fat lie and does not exist.");
      }
 else       if (exportFile.isDirectory()) {
        Util.copyDir(exportFile,new File(jarFolder,exportName));
      }
 else       if (exportName.toLowerCase().endsWith(".zip") || exportName.toLowerCase().endsWith(".jar")) {
        Util.copyFile(exportFile,new File(jarFolder,exportName));
        jarList.append(exportName);
      }
 else {
        Util.copyFile(exportFile,new File(jarFolder,exportName));
      }
    }
  }
  String exportClassPath=null;
  if (exportPlatform == PConstants.MACOSX) {
    exportClassPath="$JAVAROOT/" + jarList.join(":$JAVAROOT/");
  }
 else   if (exportPlatform == PConstants.WINDOWS) {
    exportClassPath=jarList.join(",");
  }
 else   if (exportPlatform == PConstants.LINUX) {
    exportClassPath="$APPDIR" + ":$APPDIR/lib/" + jarList.join(":$APPDIR/lib/");
  }
  StringList runOptions=new StringList();
  if (Preferences.getBoolean("run.options.memory") && !exportVariant.equals("armv6hf")) {
    runOptions.append("-Xms" + Preferences.get("run.options.memory.initial") + "m");
    runOptions.append("-Xmx" + Preferences.get("run.options.memory.maximum") + "m");
  }
  runOptions.append("-Djna.nosys=true");
  if (embedJava) {
    if (exportPlatform == PConstants.MACOSX) {
      runOptions.append("-Djava.ext.dirs=$APP_ROOT/Contents/PlugIns/jdk" + PApplet.javaVersionName + ".jdk/Contents/Home/jre/lib/ext");
    }
 else     if (exportPlatform == PConstants.WINDOWS) {
      runOptions.append("-Djava.ext.dirs=\"%EXEDIR%\\java\\lib\\ext\"");
    }
 else     if (exportPlatform == PConstants.LINUX) {
      runOptions.append("-Djava.ext.dirs=\"$APPDIR/java/lib/ext\"");
    }
  }
  if (exportPlatform == PConstants.WINDOWS) {
    runOptions.append("-Djava.library.path=\"%EXEDIR%\\lib\"");
  }
  if (exportPlatform == PConstants.MACOSX) {
    StringBuilder runOptionsXML=new StringBuilder();
    for (    String opt : runOptions) {
      runOptionsXML.append("      <string>");
      runOptionsXML.append(opt);
      runOptionsXML.append("</string>");
      runOptionsXML.append('\n');
    }
    String PLIST_TEMPLATE="Info.plist.tmpl";
    File plistTemplate=new File(sketch.getFolder(),PLIST_TEMPLATE);
    if (!plistTemplate.exists()) {
      plistTemplate=mode.getContentFile("application/" + PLIST_TEMPLATE);
    }
    File plistFile=new File(dotAppFolder,"Contents/Info.plist");
    PrintWriter pw=PApplet.createWriter(plistFile);
    String lines[]=PApplet.loadStrings(plistTemplate);
    for (int i=0; i < lines.length; i++) {
      if (lines[i].indexOf("@@") != -1) {
        StringBuilder sb=new StringBuilder(lines[i]);
        int index=0;
        while ((index=sb.indexOf("@@jvm_runtime@@")) != -1) {
          sb.replace(index,index + "@@jvm_runtime@@".length(),jvmRuntime);
        }
        while ((index=sb.indexOf("@@jvm_options_list@@")) != -1) {
          sb.replace(index,index + "@@jvm_options_list@@".length(),runOptionsXML.toString());
        }
        while ((index=sb.indexOf("@@sketch@@")) != -1) {
          sb.replace(index,index + "@@sketch@@".length(),sketch.getName());
        }
        while ((index=sb.indexOf("@@lsuipresentationmode@@")) != -1) {
          sb.replace(index,index + "@@lsuipresentationmode@@".length(),Preferences.getBoolean("export.application.present") ? "4" : "0");
        }
        lines[i]=sb.toString();
      }
      pw.print(lines[i] + "\n");
    }
    pw.flush();
    pw.close();
    if (Platform.isMacOS() && isXcodeInstalled()) {
      if (embedJava) {
        ProcessHelper.ffs("codesign","--force","--sign","-",jdkPath);
      }
      String appPath=dotAppFolder.getAbsolutePath();
      ProcessHelper.ffs("codesign","--force","--sign","-",appPath);
    }
  }
 else   if (exportPlatform == PConstants.WINDOWS) {
    File buildFile=new File(destFolder,"launch4j-build.xml");
    File configFile=new File(destFolder,"launch4j-config.xml");
    XML project=new XML("project");
    XML target=project.addChild("target");
    target.setString("name","windows");
    XML taskdef=target.addChild("taskdef");
    taskdef.setString("name","launch4j");
    taskdef.setString("classname","net.sf.launch4j.ant.Launch4jTask");
    String launchPath=mode.getContentFile("application/launch4j").getAbsolutePath();
    taskdef.setString("classpath",launchPath + "/launch4j.jar:" + launchPath + "/lib/xstream.jar");
    XML launch4j=target.addChild("launch4j");
    launch4j.setString("configFile",configFile.getAbsolutePath());
    XML config=new XML("launch4jConfig");
    config.addChild("headerType").setContent("gui");
    config.addChild("dontWrapJar").setContent("true");
    config.addChild("downloadUrl").setContent("http://java.com/download");
    File exeFile=new File(destFolder,sketch.getName() + ".exe");
    config.addChild("outfile").setContent(exeFile.getAbsolutePath());
    File iconFile=mode.getContentFile("application/sketch.ico");
    config.addChild("icon").setContent(iconFile.getAbsolutePath());
    XML clazzPath=config.addChild("classPath");
    clazzPath.addChild("mainClass").setContent(sketch.getName());
    for (    String jarName : jarList) {
      clazzPath.addChild("cp").setContent("lib/" + jarName);
    }
    XML jre=config.addChild("jre");
    if (embedJava) {
      jre.addChild("path").setContent("java");
    }
    jre.addChild("minVersion").setContent("1.8.0_74");
    for (    String opt : runOptions) {
      jre.addChild("opt").setContent(opt);
    }
    config.save(configFile);
    project.save(buildFile);
    if (!buildWindowsLauncher(buildFile,"windows")) {
      return false;
    }
    configFile.delete();
    buildFile.delete();
  }
 else {
    File shellScript=new File(destFolder,sketch.getName());
    PrintWriter pw=PApplet.createWriter(shellScript);
    pw.print("#!/bin/sh\n\n");
    pw.print("APPDIR=$(readlink -f \"$0\")\n");
    pw.print("APPDIR=$(dirname \"$APPDIR\")\n");
    if (embedJava) {
      pw.print("$APPDIR/java/bin/");
    }
    String runOptionsStr=runOptions.join(" ");
    pw.print("java " + runOptionsStr + " -Djava.library.path=\"$APPDIR:$APPDIR/lib\"" + " -cp \"" + exportClassPath + "\"" + " " + sketch.getName() + " \"$@\"\n");
    pw.flush();
    pw.close();
    String shellPath=shellScript.getAbsolutePath();
    if (!Platform.isWindows()) {
      Runtime.getRuntime().exec(new String[]{"chmod","+x",shellPath});
    }
  }
  File sourceFolder=new File(destFolder,"source");
  sourceFolder.mkdirs();
  for (  SketchCode code : sketch.getCode()) {
    try {
      code.copyTo(new File(sourceFolder,code.getFileName()));
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
  String preprocFilename=sketch.getName() + ".java";
  File preprocFile=new File(srcFolder,preprocFilename);
  if (preprocFile.exists()) {
    Util.copyFile(preprocFile,new File(sourceFolder,preprocFilename));
  }
 else {
    System.err.println("Could not copy source file: " + preprocFile.getAbsolutePath());
  }
  return true;
}
