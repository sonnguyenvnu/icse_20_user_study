@Override @SuppressWarnings("squid:S106") public void run(String... args){
  System.out.println(this.cityMapper.findByState("CA"));
}
