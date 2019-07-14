static boolean isEpoxyModelWithHolder(TypeElement type){
  return isSubtypeOfType(type.asType(),EPOXY_MODEL_WITH_HOLDER_TYPE);
}
