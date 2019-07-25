@PostConstruct public void init(){
  controlPointLoader=ServiceLoader.load(ControlPointAdapter.class);
}
