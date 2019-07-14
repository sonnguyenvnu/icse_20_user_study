@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).child(Text.create(c).textSizeSp(20).text("This component has a composite path effect of discrete + corner")).border(Border.create(c).widthDip(YogaEdge.ALL,20).color(YogaEdge.LEFT,NiceColor.RED).color(YogaEdge.TOP,NiceColor.ORANGE).color(YogaEdge.RIGHT,NiceColor.GREEN).color(YogaEdge.BOTTOM,NiceColor.BLUE).dashEffect(new float[]{10f,5f},0f).radiusDip(20f).build()).build();
}
