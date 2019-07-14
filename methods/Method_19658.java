@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Text.create(c,0,R.style.hello_world).textRes(R.string.hello_world).build();
}
