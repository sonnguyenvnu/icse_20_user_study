public Annotations<A> onPackageHierarchyOf(final Class type){
  Package pck=type.getPackage();
  String packageName=pck.getName();
  while (true) {
    if (pck != null) {
      A a=pck.getAnnotation(annotationClass);
      if (a != null) {
        annotations.add(a);
      }
    }
    int ndx=packageName.lastIndexOf('.');
    if (ndx == -1) {
      break;
    }
    packageName=packageName.substring(0,ndx);
    pck=Packages.of(type.getClassLoader(),packageName);
  }
  return this;
}
