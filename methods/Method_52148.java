protected boolean hasIgnoredAnnotation(AccessNode node){
  if (node instanceof Annotatable) {
    return hasIgnoredAnnotation((Annotatable)node);
  }
  return false;
}
