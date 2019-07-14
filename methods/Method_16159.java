public Map<Class<Entity>,MapperEntityFactory.Mapper> createMappers(){
  if (mappings == null || mappings.isEmpty()) {
    return new java.util.HashMap<>();
  }
  return mappings.stream().map(Mapping::create).reduce(MapUtils::merge).orElseGet(HashMap::new);
}
