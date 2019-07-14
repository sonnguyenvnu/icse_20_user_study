/** 
 * If true, Epoxy models added to an EpoxyController will be validated at run time to make sure they are properly used. <p> By default this is true, and it is highly recommended to enable it to prevent accidental misuse of your models. However, you may want to disable this for production builds to avoid the overhead of the runtime validation code. <p> Using a debug build flag is a great way to do this.
 */
List<Exception> processConfigurations(RoundEnvironment roundEnv){
  List<Exception> errors=new ArrayList<>();
  for (  Element element : roundEnv.getElementsAnnotatedWith(PackageEpoxyConfig.class)) {
    String packageName=elementUtils.getPackageOf(element).getQualifiedName().toString();
    if (configurationMap.containsKey(packageName)) {
      errors.add(buildEpoxyException("Only one Epoxy configuration annotation is allowed per package (%s)",packageName));
      continue;
    }
    PackageEpoxyConfig annotation=element.getAnnotation(PackageEpoxyConfig.class);
    configurationMap.put(packageName,PackageConfigSettings.Companion.create(annotation));
  }
  for (  Element element : roundEnv.getElementsAnnotatedWith(PackageModelViewConfig.class)) {
    String packageName=elementUtils.getPackageOf(element).getQualifiedName().toString();
    if (modelViewNamingMap.containsKey(packageName)) {
      errors.add(buildEpoxyException("Only one %s annotation is allowed per package (%s)",PackageModelViewConfig.class.getSimpleName(),packageName));
      continue;
    }
    ClassName rClassName=getClassParamFromAnnotation(element,PackageModelViewConfig.class,"rClass",typeUtils);
    if (rClassName == null) {
      errors.add(buildEpoxyException("Unable to get R class details from annotation %s (package: %s)",PackageModelViewConfig.class.getSimpleName(),packageName));
      continue;
    }
    String rLayoutClassString=rClassName.reflectionName();
    if (!rLayoutClassString.endsWith(".R") && !rLayoutClassString.endsWith(".R2")) {
      errors.add(buildEpoxyException("Invalid R class in %s. Was '%s' (package: %s)",PackageModelViewConfig.class.getSimpleName(),rLayoutClassString,packageName));
      continue;
    }
    PackageModelViewConfig annotation=element.getAnnotation(PackageModelViewConfig.class);
    modelViewNamingMap.put(packageName,new PackageModelViewSettings(rClassName,annotation));
  }
  return errors;
}
