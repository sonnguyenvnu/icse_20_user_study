private boolean isInterface(TypeMirror typeMirror){
  return typeMirror instanceof DeclaredType && ((DeclaredType)typeMirror).asElement().getKind() == INTERFACE;
}
