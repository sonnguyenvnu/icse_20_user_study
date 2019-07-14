private String getNamespaceSectionHeader(ConfigNamespace n,String prefix){
  String fullName=prefix + n.getName();
  if (n.isUmbrella())   fullName+=" *";
  return "==== " + fullName + " ====\n[role=\"font16\"]\n" + n.getDescription() + "\n\n";
}
