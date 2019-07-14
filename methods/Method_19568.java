@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).child(Text.create(c).textSizeSp(20).text("This component has a dash path effect on its vertical edges")).border(Border.create(c).color(YogaEdge.VERTICAL,NiceColor.RED).widthDip(YogaEdge.ALL,5).dashEffect(new float[]{20f,5f},0f).build()).build();
}
