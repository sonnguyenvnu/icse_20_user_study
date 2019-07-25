@Override public GenConfig find(){
  Optional<GenConfig> genConfig=genConfigRepository.findById(1L);
  if (genConfig.isPresent()) {
    return genConfig.get();
  }
 else {
    return new GenConfig();
  }
}
