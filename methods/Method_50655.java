@Override public ApexQualifiedName getQualifiedName(){
  if (qname == null) {
    ASTUserClass parent=this.getFirstParentOfType(ASTUserClass.class);
    if (parent != null) {
      qname=ApexQualifiedName.ofNestedClass(parent.getQualifiedName(),this);
    }
 else {
      qname=ApexQualifiedName.ofOuterClass(this);
    }
  }
  return qname;
}
