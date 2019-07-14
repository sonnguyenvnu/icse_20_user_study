@Override public ApexQualifiedName getClassName(){
  if (isClass()) {
    return this;
  }
  return new ApexQualifiedName(this.nameSpace,this.classes,null);
}
