@Override public void visitClassType(String name){
  if (TRACE) {
    println("visitClassType:");
    printlnIndent("name: " + name);
  }
  name=ClassLoaderUtil.fromInternalForm(name);
  this.type=ClassLoaderUtil.getClass(name);
}
