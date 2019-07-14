@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).child(Text.create(c).textSizeSp(20).text("This component has varying corner radii")).border(Border.create(c).widthDip(YogaEdge.ALL,3).color(YogaEdge.LEFT,Color.BLACK).color(YogaEdge.TOP,NiceColor.GREEN).color(YogaEdge.BOTTOM,NiceColor.BLUE).color(YogaEdge.RIGHT,NiceColor.RED).radiusDip(Corner.TOP_LEFT,10).radiusDip(Corner.TOP_RIGHT,5).radiusDip(Corner.BOTTOM_RIGHT,20).radiusDip(Corner.BOTTOM_LEFT,30).build()).build();
}
