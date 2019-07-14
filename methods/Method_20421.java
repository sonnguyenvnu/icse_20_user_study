static boolean isController(TypeElement element){
  return isSubtypeOfType(element.asType(),EPOXY_CONTROLLER_TYPE);
}
