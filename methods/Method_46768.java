public Resource[] resolveMapperLocations(){
  return Stream.of(Optional.ofNullable(this.mapperLocations).orElse(new String[0])).flatMap(location -> Stream.of(getResources(location))).toArray(Resource[]::new);
}
