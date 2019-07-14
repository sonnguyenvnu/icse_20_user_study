@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop(resType=STRING) String subtitle){
  if (Math.random() >= 0.7) {
    throw new RuntimeException("Oh no, a random error!");
  }
  return Text.create(c).text(subtitle).textSizeDip(18).build();
}
