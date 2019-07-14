@Override public void visitInnerClass(String name,String outerName,String innerName,int access){
  if (!this.outerName.replace('.','/').equals(outerName)) {
    return;
  }
  if (innerClasses == null) {
    innerClasses=new ArrayList<>();
  }
  if (!innerClasses.contains(name.replace('/','.'))) {
    innerClasses.add(name.replace('/','.'));
  }
  packages.put(innerName,name.replace('/','.'));
}
