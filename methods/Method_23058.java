@Override public void execute() throws BuildException {
  if (outputDirectory == null) {
    throw new IllegalStateException("Output directory is required.");
  }
  if (!outputDirectory.exists()) {
    throw new IllegalStateException("Output directory does not exist.");
  }
  if (!outputDirectory.isDirectory()) {
    throw new IllegalStateException("Invalid output directory.");
  }
  if (name == null) {
    throw new IllegalStateException("Name is required.");
  }
  if (displayName == null) {
    throw new IllegalStateException("Display name is required.");
  }
  if (identifier == null) {
    throw new IllegalStateException("Identifier is required.");
  }
  if (iconFile != null) {
    if (!iconFile.exists()) {
      throw new IllegalStateException("Icon does not exist.");
    }
    if (iconFile.isDirectory()) {
      throw new IllegalStateException("Invalid icon.");
    }
  }
  if (shortVersion == null) {
    throw new IllegalStateException("Short version is required.");
  }
  if (signature == null) {
    throw new IllegalStateException("Signature is required.");
  }
  if (signature.length() != 4) {
    throw new IllegalStateException("Invalid signature.");
  }
  if (copyright == null) {
    throw new IllegalStateException("Copyright is required.");
  }
  if (mainClassName == null) {
    throw new IllegalStateException("Main class name is required.");
  }
  try {
    System.out.println("Creating app bundle: " + name);
    File rootDirectory=new File(outputDirectory,name + ".app");
    delete(rootDirectory);
    rootDirectory.mkdir();
    File contentsDirectory=new File(rootDirectory,"Contents");
    contentsDirectory.mkdir();
    File macOSDirectory=new File(contentsDirectory,"MacOS");
    macOSDirectory.mkdir();
    File javaDirectory=new File(contentsDirectory,"Java");
    javaDirectory.mkdir();
    File plugInsDirectory=new File(contentsDirectory,"PlugIns");
    plugInsDirectory.mkdir();
    File resourcesDirectory=new File(contentsDirectory,"Resources");
    resourcesDirectory.mkdir();
    File infoPlistFile=new File(contentsDirectory,"Info.plist");
    infoPlistFile.createNewFile();
    writeInfoPlist(infoPlistFile);
    File pkgInfoFile=new File(contentsDirectory,"PkgInfo");
    pkgInfoFile.createNewFile();
    writePkgInfo(pkgInfoFile);
    File executableFile=new File(macOSDirectory,executableName);
    copy(getClass().getResource(EXECUTABLE_NAME),executableFile);
    executableFile.setExecutable(true,false);
    copyResources(resourcesDirectory);
    copyRuntime(plugInsDirectory);
    copyClassPathEntries(javaDirectory);
    copyClassPathRefEntries(javaDirectory);
    copyLibraryPathEntries(macOSDirectory);
    copyIcon(resourcesDirectory);
    copyBundleIcons(resourcesDirectory);
  }
 catch (  IOException exception) {
    throw new BuildException(exception);
  }
}
