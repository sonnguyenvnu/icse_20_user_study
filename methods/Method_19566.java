@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).layoutDirection(YogaDirection.RTL).child(Text.create(c).textSizeSp(20).text("This component is RTL")).border(Border.create(c).color(YogaEdge.START,NiceColor.RED).color(YogaEdge.TOP,NiceColor.YELLOW).color(YogaEdge.END,NiceColor.GREEN).color(YogaEdge.BOTTOM,NiceColor.BLUE).widthDip(YogaEdge.START,2).widthDip(YogaEdge.TOP,4).widthDip(YogaEdge.END,8).widthDip(YogaEdge.BOTTOM,16).build()).build();
}
