public HttpHeaders index(){
  list=PetData.getNames().stream().map(petName -> new Pet(UUID.randomUUID().toString(),PetData.getRandomBreed(),petName,PetData.getRandomDoB())).collect(Collectors.toList());
  return new DefaultHttpHeaders("index").disableCaching();
}
