static boolean isEpoxyModel(TypeMirror type){
  return isSubtypeOfType(type,EPOXY_MODEL_TYPE) || isSubtypeOfType(type,UNTYPED_EPOXY_MODEL_TYPE);
}
