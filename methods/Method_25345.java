@Override public boolean matches(AnnotationTree annotationTree,VisitorState state){
  Tree type=annotationTree.getAnnotationType();
  if (type.getKind() == Tree.Kind.IDENTIFIER && type instanceof JCTree.JCIdent) {
    JCTree.JCIdent jcIdent=(JCTree.JCIdent)type;
    return jcIdent.sym.getQualifiedName().contentEquals(annotationClassName);
  }
 else   if (type.getKind() == Tree.Kind.MEMBER_SELECT && type instanceof JCTree.JCFieldAccess) {
    JCTree.JCFieldAccess jcFieldAccess=(JCTree.JCFieldAccess)type;
    return jcFieldAccess.sym.getQualifiedName().contentEquals(annotationClassName);
  }
 else {
    return false;
  }
}
