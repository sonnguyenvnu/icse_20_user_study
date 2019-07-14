private List<ConfigNamespace> getSortedChildNamespaces(ConfigNamespace n){
  return getSortedChildren(n,ConfigElement::isNamespace);
}
