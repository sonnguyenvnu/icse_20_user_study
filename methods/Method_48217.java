public static PathIdentifier parse(ConfigNamespace root,String path){
  Preconditions.checkNotNull(root);
  if (StringUtils.isBlank(path))   return new PathIdentifier(root,new String[]{},false);
  String[] components=getComponents(path);
  Preconditions.checkArgument(components.length > 0,"Empty path provided: %s",path);
  List<String> umbrellaElements=Lists.newArrayList();
  ConfigNamespace parent=root;
  ConfigElement last=root;
  boolean lastIsUmbrella=false;
  for (int i=0; i < components.length; i++) {
    if (parent.isUmbrella() && !lastIsUmbrella) {
      umbrellaElements.add(components[i]);
      lastIsUmbrella=true;
    }
 else {
      last=parent.getChild(components[i]);
      Preconditions.checkArgument(last != null,"Unknown configuration element in namespace [%s]: %s",parent.toString(),components[i]);
      if (i + 1 < components.length) {
        Preconditions.checkArgument(last instanceof ConfigNamespace,"Expected namespace at position [%s] of [%s] but got: %s",i,path,last);
        parent=(ConfigNamespace)last;
      }
      lastIsUmbrella=false;
    }
  }
  return new PathIdentifier(last,umbrellaElements.toArray(new String[umbrellaElements.size()]),lastIsUmbrella);
}
