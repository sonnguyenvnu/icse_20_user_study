/** 
 * Reads the Module, ModulePackages and ModuleMainClass attributes and visit them.
 * @param classVisitor the current class visitor
 * @param context information about the class being parsed.
 * @param moduleOffset the offset of the Module attribute (excluding the attribute_info'sattribute_name_index and attribute_length fields).
 * @param modulePackagesOffset the offset of the ModulePackages attribute (excluding theattribute_info's attribute_name_index and attribute_length fields), or 0.
 * @param moduleMainClass the string corresponding to the ModuleMainClass attribute, or null.
 */
private void readModuleAttributes(final ClassVisitor classVisitor,final Context context,final int moduleOffset,final int modulePackagesOffset,final String moduleMainClass){
  char[] buffer=context.charBuffer;
  int currentOffset=moduleOffset;
  String moduleName=readModule(currentOffset,buffer);
  int moduleFlags=readUnsignedShort(currentOffset + 2);
  String moduleVersion=readUTF8(currentOffset + 4,buffer);
  currentOffset+=6;
  ModuleVisitor moduleVisitor=classVisitor.visitModule(moduleName,moduleFlags,moduleVersion);
  if (moduleVisitor == null) {
    return;
  }
  if (moduleMainClass != null) {
    moduleVisitor.visitMainClass(moduleMainClass);
  }
  if (modulePackagesOffset != 0) {
    int packageCount=readUnsignedShort(modulePackagesOffset);
    int currentPackageOffset=modulePackagesOffset + 2;
    while (packageCount-- > 0) {
      moduleVisitor.visitPackage(readPackage(currentPackageOffset,buffer));
      currentPackageOffset+=2;
    }
  }
  int requiresCount=readUnsignedShort(currentOffset);
  currentOffset+=2;
  while (requiresCount-- > 0) {
    String requires=readModule(currentOffset,buffer);
    int requiresFlags=readUnsignedShort(currentOffset + 2);
    String requiresVersion=readUTF8(currentOffset + 4,buffer);
    currentOffset+=6;
    moduleVisitor.visitRequire(requires,requiresFlags,requiresVersion);
  }
  int exportsCount=readUnsignedShort(currentOffset);
  currentOffset+=2;
  while (exportsCount-- > 0) {
    String exports=readPackage(currentOffset,buffer);
    int exportsFlags=readUnsignedShort(currentOffset + 2);
    int exportsToCount=readUnsignedShort(currentOffset + 4);
    currentOffset+=6;
    String[] exportsTo=null;
    if (exportsToCount != 0) {
      exportsTo=new String[exportsToCount];
      for (int i=0; i < exportsToCount; ++i) {
        exportsTo[i]=readModule(currentOffset,buffer);
        currentOffset+=2;
      }
    }
    moduleVisitor.visitExport(exports,exportsFlags,exportsTo);
  }
  int opensCount=readUnsignedShort(currentOffset);
  currentOffset+=2;
  while (opensCount-- > 0) {
    String opens=readPackage(currentOffset,buffer);
    int opensFlags=readUnsignedShort(currentOffset + 2);
    int opensToCount=readUnsignedShort(currentOffset + 4);
    currentOffset+=6;
    String[] opensTo=null;
    if (opensToCount != 0) {
      opensTo=new String[opensToCount];
      for (int i=0; i < opensToCount; ++i) {
        opensTo[i]=readModule(currentOffset,buffer);
        currentOffset+=2;
      }
    }
    moduleVisitor.visitOpen(opens,opensFlags,opensTo);
  }
  int usesCount=readUnsignedShort(currentOffset);
  currentOffset+=2;
  while (usesCount-- > 0) {
    moduleVisitor.visitUse(readClass(currentOffset,buffer));
    currentOffset+=2;
  }
  int providesCount=readUnsignedShort(currentOffset);
  currentOffset+=2;
  while (providesCount-- > 0) {
    String provides=readClass(currentOffset,buffer);
    int providesWithCount=readUnsignedShort(currentOffset + 2);
    currentOffset+=4;
    String[] providesWith=new String[providesWithCount];
    for (int i=0; i < providesWithCount; ++i) {
      providesWith[i]=readClass(currentOffset,buffer);
      currentOffset+=2;
    }
    moduleVisitor.visitProvide(provides,providesWith);
  }
  moduleVisitor.visitEnd();
}
