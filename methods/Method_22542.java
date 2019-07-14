public String initLoader(Base base,String className) throws Exception {
  File modeDirectory=new File(folder,getTypeName());
  if (modeDirectory.exists()) {
    Messages.log("checking mode folder regarding class name " + className);
    if (className == null) {
      String shortName=folder.getName();
      File mainJar=new File(modeDirectory,shortName + ".jar");
      if (mainJar.exists()) {
        className=findClassInZipFile(shortName,mainJar);
      }
 else {
        throw new IgnorableException(mainJar.getAbsolutePath() + " does not exist.");
      }
      if (className == null) {
        throw new IgnorableException("Could not find " + shortName + " class inside " + mainJar.getAbsolutePath());
      }
    }
    ArrayList<URL> extraUrls=new ArrayList<>();
    if (imports != null && imports.size() > 0) {
      HashMap<String,Mode> installedModes=new HashMap<>();
      for (      Mode m : base.getModeList()) {
        installedModes.put(m.getClass().getName(),m);
      }
      for (      String modeImport : imports) {
        if (installedModes.containsKey(modeImport)) {
          Messages.log("Found mode dependency " + modeImport);
          File modeFolder=installedModes.get(modeImport).getFolder();
          File[] archives=Util.listJarFiles(new File(modeFolder,"mode"));
          if (archives != null && archives.length > 0) {
            for (int i=0; i < archives.length; i++) {
              extraUrls.add(archives[i].toURI().toURL());
            }
          }
        }
 else {
          throw new IgnorableException("Can't load " + className + " because the import " + modeImport + " could not be found. ");
        }
      }
    }
    File[] archives=Util.listJarFiles(modeDirectory);
    if (archives != null && archives.length > 0) {
      int arrLen=archives.length + extraUrls.size();
      URL[] urlList=new URL[arrLen];
      int j=0;
      for (; j < extraUrls.size(); j++) {
        urlList[j]=extraUrls.get(j);
      }
      for (int k=0; k < archives.length; k++, j++) {
        Messages.log("Found archive " + archives[k] + " for " + getName());
        urlList[j]=archives[k].toURI().toURL();
      }
      loader=new URLClassLoader(urlList);
      Messages.log("loading above JARs with loader " + loader);
    }
  }
  if (loader == null) {
    loader=Thread.currentThread().getContextClassLoader();
  }
  return className;
}
