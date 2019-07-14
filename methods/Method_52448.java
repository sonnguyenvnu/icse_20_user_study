@Override public String toString(){
  return getImage() + ":" + location.getBeginLine() + ":" + location.getClass() + (this.isMethodOrConstructorInvocation() ? "(method call)" : "");
}
