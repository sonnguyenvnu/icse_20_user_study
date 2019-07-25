@PostConstruct private void init(){
  Map<String,Object> map=applicationContext.getBeansWithAnnotation(Path.class);
  if (map != null) {
    Set<Class<?>> pathClasses=map.values().stream().map(o -> o.getClass()).collect(Collectors.toSet());
    registerClasses(pathClasses);
  }
}
