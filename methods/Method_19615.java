@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop(resType=STRING) String text){
  return Column.create(c).paddingDip(YogaEdge.ALL,8).child(Text.create(c).text(text).textSizeDip(14).textColor(GRAY).textStyle(ITALIC)).build();
}
