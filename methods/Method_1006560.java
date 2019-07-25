private TypeName register(TypeName typeName){
  String pkg=typeName.getPackageName();
  if (typeName.getSimpleName().equals("*")) {
    registeredPackages.add(pkg);
    imports.add(typeName);
  }
 else   if (shouldImport(typeName)) {
    if ("".equals(pkg) && typeName.findMatchingSimpleName(imports) != null) {
      return typeName;
    }
    if (!registeredPackages.contains(pkg)) {
      imports.add(typeName);
    }
  }
  return typeName;
}
