private void collectModifiers(JavaNode node,List<String> extras){
  if (node instanceof AccessNode) {
    AccessNode accessNode=(AccessNode)node;
    if (accessNode.isPackagePrivate()) {
      extras.add("package private");
    }
    if (accessNode.isPrivate()) {
      extras.add("private");
    }
    if (accessNode.isPublic()) {
      extras.add("public");
    }
    if (accessNode.isProtected()) {
      extras.add("protected");
    }
    if (accessNode.isAbstract()) {
      extras.add("abstract");
    }
    if (accessNode.isStatic()) {
      extras.add("static");
    }
    if (accessNode.isFinal()) {
      extras.add("final");
    }
    if (accessNode.isSynchronized()) {
      extras.add("synchronized");
    }
    if (accessNode.isNative()) {
      extras.add("native");
    }
    if (accessNode.isStrictfp()) {
      extras.add("strict");
    }
    if (accessNode.isTransient()) {
      extras.add("transient");
    }
    if (accessNode.isDefault()) {
      extras.add("default");
    }
  }
}
