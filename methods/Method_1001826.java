public boolean shadowing(Stereotype stereotype){
  if (stereotype != null) {
    checkStereotype(stereotype);
    final String value2=getValue("shadowing" + stereotype.getLabel(Guillemet.DOUBLE_COMPARATOR));
    if (value2 != null) {
      return value2.equalsIgnoreCase("true");
    }
  }
  final String value=getValue("shadowing");
  if ("false".equalsIgnoreCase(value)) {
    return false;
  }
  if ("true".equalsIgnoreCase(value)) {
    return true;
  }
  if (strictUmlStyle()) {
    return false;
  }
  return true;
}
