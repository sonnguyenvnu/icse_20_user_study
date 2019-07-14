private List<ConfigOption<?>> getSortedChildOptions(ConfigNamespace n){
  return getSortedChildren(n,arg0 -> arg0.isOption() && !((ConfigOption)arg0).isHidden());
}
