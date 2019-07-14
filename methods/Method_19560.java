@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).child(Text.create(c).textSizeSp(20).text("This component has each border specified to a different color + width")).border(Border.create(c).color(YogaEdge.LEFT,NiceColor.RED).color(YogaEdge.TOP,NiceColor.YELLOW).color(YogaEdge.RIGHT,NiceColor.GREEN).color(YogaEdge.BOTTOM,NiceColor.BLUE).widthDip(YogaEdge.LEFT,2).widthDip(YogaEdge.TOP,4).widthDip(YogaEdge.RIGHT,8).widthDip(YogaEdge.BOTTOM,16).build()).build();
}
