@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).child(Text.create(c).textSizeSp(20).text("This component has all borders specified to the same width, but not colors")).border(Border.create(c).color(YogaEdge.LEFT,NiceColor.RED).color(YogaEdge.TOP,NiceColor.YELLOW).color(YogaEdge.RIGHT,NiceColor.GREEN).color(YogaEdge.BOTTOM,NiceColor.BLUE).widthDip(YogaEdge.ALL,5).build()).build();
}
