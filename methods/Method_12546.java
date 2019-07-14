private static List<Map<String,Object>> convertFlywayMigrations(List<Map<String,Object>> migrations){
  return migrations.stream().map(migration -> {
    Map<String,Object> converted=new LinkedHashMap<>(migration);
    if (migration.get("installedOn") instanceof Long) {
      converted.put("installedOn",new Date((Long)migration.get("installedOn")));
    }
    return converted;
  }
).collect(toList());
}
