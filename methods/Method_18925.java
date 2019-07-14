private static InjectPropModel updateInjectPropWithCachedName(InjectPropModel prop,@Nullable List<String> cachedPropNames,int index){
  final String name=cachedPropNames != null && index < cachedPropNames.size() ? cachedPropNames.get(index) : null;
  return name != null ? prop.withName(name) : prop;
}
