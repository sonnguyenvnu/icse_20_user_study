@Override public void serialize(Set<ModuleType> dataObjects,OutputStreamWriter writer) throws Exception {
  Map<String,List<? extends ModuleType>> map=createMapByType(dataObjects);
  gson.toJson(map,writer);
}
