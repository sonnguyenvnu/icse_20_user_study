public String initLoader(String className) throws Exception {
  File modeDirectory=new File(folder,getTypeName());
  if (modeDirectory.exists()) {
    Messages.log("checking mode folder regarding " + className);
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
    File[] archives=Util.listJarFiles(modeDirectory);
    if (archives != null && archives.length > 0) {
      URL[] urlList=new URL[archives.length];
      for (int j=0; j < urlList.length; j++) {
        Messages.log("Found archive " + archives[j] + " for " + getName());
        urlList[j]=archives[j].toURI().toURL();
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
