@Override public Resource[] resolveMapperLocations(){
  Map<String,Resource> resources=new HashMap<>();
  Set<String> locations;
  if (this.getMapperLocations() == null) {
    locations=new HashSet<>();
  }
 else {
    locations=Arrays.stream(getMapperLocations()).collect(Collectors.toSet());
  }
  locations.add(defaultMapperLocation);
  if (mybatisMappers != null) {
    mybatisMappers.stream().map(MybatisMapperCustomizer::getIncludes).flatMap(Arrays::stream).forEach(locations::add);
  }
  for (  String mapperLocation : locations) {
    Resource[] mappers;
    try {
      mappers=new PathMatchingResourcePatternResolver().getResources(mapperLocation);
      for (      Resource mapper : mappers) {
        resources.put(mapper.getURL().toString(),mapper);
      }
    }
 catch (    IOException e) {
    }
  }
  Set<String> excludes=new HashSet<>();
  if (mybatisMappers != null) {
    mybatisMappers.stream().map(MybatisMapperCustomizer::getExcludes).flatMap(Arrays::stream).forEach(excludes::add);
  }
  if (mapperLocationExcludes != null && mapperLocationExcludes.length > 0) {
    for (    String exclude : mapperLocationExcludes) {
      excludes.add(exclude);
    }
  }
  PathMatchingResourcePatternResolver resourcePatternResolver=new PathMatchingResourcePatternResolver();
  for (  String mapperLocationExclude : excludes) {
    try {
      Resource[] excludesMappers=resourcePatternResolver.getResources(mapperLocationExclude);
      for (      Resource excludesMapper : excludesMappers) {
        resources.remove(excludesMapper.getURL().toString());
      }
    }
 catch (    IOException e) {
    }
  }
  Resource[] mapperLocations=new Resource[resources.size()];
  mapperLocations=resources.values().toArray(mapperLocations);
  return mapperLocations;
}
