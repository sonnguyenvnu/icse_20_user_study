@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Column.create(c).backgroundColor(Color.WHITE).child(Text.create(c).textSizeSp(20).text("Playground sample")).build();
}
