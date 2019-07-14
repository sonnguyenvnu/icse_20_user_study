private void printNamespace(ConfigNamespace n,String prefix){
  String newPrefix=prefix + n.getName() + ".";
  if (n.isUmbrella())   newPrefix+="[X].";
  if (n.isRoot())   newPrefix="";
  if (!Iterables.isEmpty(getSortedChildOptions(n))) {
    stream.println(getNamespaceSectionHeader(n,prefix));
    stream.println(getTableHeader());
    for (    ConfigOption<?> o : getSortedChildOptions(n)) {
      stream.println(getTableLineForOption(o,newPrefix));
    }
    stream.println(TABLE_FOOTER_LINES);
  }
  for (  ConfigNamespace cn : getSortedChildNamespaces(n)) {
    printNamespace(cn,newPrefix);
  }
}
