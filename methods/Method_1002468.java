private static Set<PathSpec> validate(DataMap filteredPathSpecs,Set<PathSpec> paths){
  final Set<PathSpec> result=new HashSet<PathSpec>();
  for (  PathSpec p : paths) {
    final List<String> components=p.getPathComponents();
    DataMap currentMap=filteredPathSpecs;
    boolean isPresent=true;
    for (int i=0; i < components.size(); ++i) {
      final String currentComponent=components.get(i);
      final Object currentValue=currentMap.get(currentComponent);
      if (currentValue instanceof DataMap) {
        @SuppressWarnings("unchecked") final DataMap valueMap=(DataMap)currentValue;
        currentMap=valueMap;
      }
 else {
        isPresent=currentMap.containsKey(currentComponent);
        break;
      }
    }
    if (isPresent) {
      result.add(p);
    }
  }
  return result;
}
