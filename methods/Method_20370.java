/** 
 * Check each controller for super classes that also have auto models. For each super class with auto model we add those models to the auto models of the generated class, so that a generated class contains all the models of its super classes combined. <p> One caveat is that if a sub class is in a different package than its super class we can't include auto models that are package private, otherwise the generated class won't compile.
 */
private void updateClassesForInheritance(Map<TypeElement,ControllerClassInfo> controllerClassMap){
  for (  Entry<TypeElement,ControllerClassInfo> entry : controllerClassMap.entrySet()) {
    TypeElement thisClass=entry.getKey();
    Map<TypeElement,ControllerClassInfo> otherClasses=new LinkedHashMap<>(controllerClassMap);
    otherClasses.remove(thisClass);
    for (    Entry<TypeElement,ControllerClassInfo> otherEntry : otherClasses.entrySet()) {
      TypeElement otherClass=otherEntry.getKey();
      if (!isSubtype(thisClass,otherClass,typeUtils)) {
        continue;
      }
      Set<ControllerModelField> otherControllerModelFields=otherEntry.getValue().getModels();
      if (belongToTheSamePackage(thisClass,otherClass,elementUtils)) {
        entry.getValue().addModels(otherControllerModelFields);
      }
 else {
        for (        ControllerModelField controllerModelField : otherControllerModelFields) {
          if (!controllerModelField.getPackagePrivate()) {
            entry.getValue().addModel(controllerModelField);
          }
        }
      }
    }
  }
}
