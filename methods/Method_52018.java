@Override protected boolean structurallyEquals(JavaQualifiedName qname){
  JavaOperationQualifiedName that=(JavaOperationQualifiedName)qname;
  return isLambda == that.isLambda && this.operation.equals(that.operation) && this.parent.equals(that.parent);
}
