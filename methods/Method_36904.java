private List<Class<?>> lookupCellTypes(Class<?> cellClass){
  List<Class<?>> eventTypes=cellTypesCache.get(cellClass);
  if (eventTypes == null) {
    eventTypes=new ArrayList<>();
    Class<?> clazz=cellClass;
    while (clazz != null && !clazz.equals(BaseCell.class)) {
      eventTypes.add(clazz);
      clazz=clazz.getSuperclass();
    }
    cellTypesCache.put(cellClass,eventTypes);
  }
  return eventTypes;
}
