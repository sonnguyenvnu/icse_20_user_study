private boolean isSamePackage(AbstractJavaTypeNode node){
  String name=node.getImage();
  return name.substring(0,name.lastIndexOf('.')).equals(currentPackage);
}
