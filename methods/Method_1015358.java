@GetMapping(path="/applications",produces=MediaType.APPLICATION_JSON_VALUE) public Flux<Application> applications(){
  return registry.getInstances().filter(Instance::isRegistered).groupBy(instance -> instance.getRegistration().getName()).flatMap(grouped -> toApplication(grouped.key(),grouped));
}
