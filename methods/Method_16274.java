@Override public Map<ObjectMetadata.ObjectType,List<? extends ObjectMetadata>> getMetas(){
  return parserRepo.computeIfAbsent(DataSourceHolder.currentDatabaseType(),t -> new HashMap<>()).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,entry -> {
    try {
      return entry.getValue().parseAll();
    }
 catch (    SQLException e) {
      log.error("parse meta {} error",entry.getKey(),e);
      return new ArrayList<>();
    }
  }
));
}
