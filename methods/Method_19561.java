@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).child(Text.create(c).textSizeSp(20).text("This component has all borders specified to the same color, but not width")).border(Border.create(c).color(YogaEdge.ALL,NiceColor.MAGENTA).widthDip(YogaEdge.LEFT,2).widthDip(YogaEdge.TOP,4).widthDip(YogaEdge.RIGHT,8).widthDip(YogaEdge.BOTTOM,16).build()).build();
}
