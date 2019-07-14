private static boolean hasMatchingField(MethodParamModel param,ImmutableList<FieldModel> fields){
  for (  FieldModel field : fields) {
    if (param.getName().equals(field.field.name) && (param.getTypeName().box().equals(field.field.type.box()) || isFromEventTypeSpecifiedInAnnotation(param,field.field.type))) {
      return true;
    }
  }
  return false;
}
