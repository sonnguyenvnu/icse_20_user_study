private void writeInfoPlist(File file) throws IOException {
  FileOutputStream output=new FileOutputStream(file);
  PropertyLister plist=new PropertyLister(output);
  plist.writeStartDocument();
  plist.writeStartDictElement();
  plist.writeProperty("CFBundleDevelopmentRegion","English");
  plist.writeProperty("CFBundleExecutable",executableName);
  plist.writeProperty("CFBundleIconFile",(iconFile == null) ? DEFAULT_ICON_NAME : iconFile.getName());
  plist.writeProperty("CFBundleIdentifier",identifier);
  plist.writeProperty("CFBundleDisplayName",displayName);
  plist.writeProperty("CFBundleInfoDictionaryVersion","6.0");
  plist.writeProperty("CFBundleName",name);
  plist.writeProperty("CFBundlePackageType",OS_TYPE_CODE);
  plist.writeProperty("CFBundleShortVersionString",shortVersion);
  plist.writeProperty("CFBundleVersion",version);
  plist.writeProperty("CFBundleSignature",signature);
  plist.writeProperty("NSHumanReadableCopyright",copyright);
  if (getInfo != null) {
    plist.writeProperty("CFBundleGetInfoString",getInfo);
  }
  if (applicationCategory != null) {
    plist.writeProperty("LSApplicationCategoryType",applicationCategory);
  }
  if (minimumSystem != null) {
    plist.writeProperty("LSMinimumSystemVersion",minimumSystem);
  }
  if (highResolutionCapable) {
    plist.writeKey("NSHighResolutionCapable");
    plist.writeBoolean(true);
  }
  if (runtime != null) {
    plist.writeProperty("JVMRuntime",runtime.getDir().getParentFile().getParentFile().getName());
  }
  if (privileged != null) {
    plist.writeProperty("JVMRunPrivileged",privileged);
  }
  if (workingDirectory != null) {
    plist.writeProperty("WorkingDirectory",workingDirectory);
  }
  plist.writeProperty("JVMMainClassName",mainClassName);
  plist.writeKey("CFBundleDocumentTypes");
  plist.writeStartArrayElement();
  for (  BundleDocument bundleDocument : bundleDocuments) {
    plist.writeStartDictElement();
    plist.writeKey("CFBundleTypeExtensions");
    plist.writeStartArrayElement();
    for (    String extension : bundleDocument.getExtensions()) {
      plist.writeString(extension);
    }
    plist.writeEndElement();
    if (bundleDocument.hasIcon()) {
      plist.writeKey("CFBundleTypeIconFile");
      plist.writeString(bundleDocument.getIconName());
    }
    plist.writeKey("CFBundleTypeName");
    plist.writeString(bundleDocument.getName());
    plist.writeKey("CFBundleTypeRole");
    plist.writeString(bundleDocument.getRole());
    plist.writeKey("LSTypeIsPackage");
    plist.writeBoolean(bundleDocument.isPackage());
    plist.writeEndElement();
  }
  plist.writeEndElement();
  plist.writeKey("LSArchitecturePriority");
  plist.writeStartArrayElement();
  for (  String architecture : architectures) {
    plist.writeString(architecture);
  }
  plist.writeEndElement();
  plist.writeKey("LSEnvironment");
  plist.writeStartDictElement();
  plist.writeKey("LC_CTYPE");
  plist.writeString("UTF-8");
  plist.writeEndElement();
  plist.writeKey("JVMOptions");
  plist.writeStartArrayElement();
  for (  String option : options) {
    plist.writeString(option);
  }
  plist.writeEndElement();
  plist.writeKey("JVMArguments");
  plist.writeStartArrayElement();
  for (  String argument : arguments) {
    plist.writeString(argument);
  }
  plist.writeEndElement();
  plist.writeEndElement();
  plist.writeEndDocument();
}
