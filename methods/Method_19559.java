@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).child(Text.create(c).textSizeSp(20).text("This component has a path effect with multiple colors")).border(Border.create(c).color(YogaEdge.LEFT,NiceColor.RED).color(YogaEdge.TOP,NiceColor.ORANGE).color(YogaEdge.RIGHT,NiceColor.GREEN).color(YogaEdge.BOTTOM,NiceColor.BLUE).widthDip(YogaEdge.ALL,5).discreteEffect(5f,10f).build()).build();
}
