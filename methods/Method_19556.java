@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).child(Text.create(c).textSizeSp(20).text("This component has all borders specified to the same color + width")).border(Border.create(c).color(YogaEdge.ALL,NiceColor.BLUE).widthDip(YogaEdge.ALL,5).build()).build();
}
