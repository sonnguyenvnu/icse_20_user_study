private boolean hasAnnotation(String annotation,Symbol member){
  for (  Attribute.Compound attribute : member.getAnnotationMirrors()) {
    if (annotation.equals(attribute.type.toString())) {
      return true;
    }
  }
  return false;
}
