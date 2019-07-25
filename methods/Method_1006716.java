/** 
 * Link classes. Not threadsafe, should be run in a single-threaded context.
 * @param classNameToClassInfo map from class name to class info
 * @param packageNameToPackageInfo map from package name to package info
 * @param moduleNameToModuleInfo map from module name to module info
 */
void link(final Map<String,ClassInfo> classNameToClassInfo,final Map<String,PackageInfo> packageNameToPackageInfo,final Map<String,ModuleInfo> moduleNameToModuleInfo){
  boolean isModuleDescriptor=false;
  boolean isPackageDescriptor=false;
  ClassInfo classInfo=null;
  if (className.equals("module-info")) {
    isModuleDescriptor=true;
  }
 else   if (className.equals("package-info") || className.endsWith(".package-info")) {
    isPackageDescriptor=true;
  }
 else {
    classInfo=ClassInfo.addScannedClass(className,classModifiers,isExternalClass,classNameToClassInfo,classpathElement,classfileResource);
    classInfo.setModifiers(classModifiers);
    classInfo.setIsInterface(isInterface);
    classInfo.setIsAnnotation(isAnnotation);
    if (superclassName != null) {
      classInfo.addSuperclass(superclassName,classNameToClassInfo);
    }
    if (implementedInterfaces != null) {
      for (      final String interfaceName : implementedInterfaces) {
        classInfo.addImplementedInterface(interfaceName,classNameToClassInfo);
      }
    }
    if (classAnnotations != null) {
      for (      final AnnotationInfo classAnnotation : classAnnotations) {
        classInfo.addClassAnnotation(classAnnotation,classNameToClassInfo);
      }
    }
    if (classContainmentEntries != null) {
      ClassInfo.addClassContainment(classContainmentEntries,classNameToClassInfo);
    }
    if (annotationParamDefaultValues != null) {
      classInfo.addAnnotationParamDefaultValues(annotationParamDefaultValues);
    }
    if (fullyQualifiedDefiningMethodName != null) {
      classInfo.addFullyQualifiedDefiningMethodName(fullyQualifiedDefiningMethodName);
    }
    if (fieldInfoList != null) {
      classInfo.addFieldInfo(fieldInfoList,classNameToClassInfo);
    }
    if (methodInfoList != null) {
      classInfo.addMethodInfo(methodInfoList,classNameToClassInfo);
    }
    if (typeSignature != null) {
      classInfo.setTypeSignature(typeSignature);
    }
    if (refdClassNames != null) {
      classInfo.addReferencedClassNames(refdClassNames);
    }
  }
  PackageInfo packageInfo=null;
  if (!isModuleDescriptor) {
    final String packageName=PackageInfo.getParentPackageName(className);
    packageInfo=PackageInfo.getOrCreatePackage(packageName,packageNameToPackageInfo);
    if (isPackageDescriptor) {
      packageInfo.addAnnotations(classAnnotations);
    }
 else     if (classInfo != null) {
      packageInfo.addClassInfo(classInfo);
      classInfo.packageInfo=packageInfo;
    }
  }
  final String moduleName=classpathElement.getModuleName();
  if (moduleName != null) {
    ModuleInfo moduleInfo=moduleNameToModuleInfo.get(moduleName);
    if (moduleInfo == null) {
      moduleNameToModuleInfo.put(moduleName,moduleInfo=new ModuleInfo(classfileResource.getModuleRef(),classpathElement));
    }
    if (isModuleDescriptor) {
      moduleInfo.addAnnotations(classAnnotations);
    }
    if (classInfo != null) {
      moduleInfo.addClassInfo(classInfo);
      classInfo.moduleInfo=moduleInfo;
    }
    if (packageInfo != null) {
      moduleInfo.addPackageInfo(packageInfo);
    }
  }
}
