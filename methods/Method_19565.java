@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).child(Text.create(c).textSizeSp(20).text("This component has a dash path effect applied")).border(Border.create(c).color(YogaEdge.ALL,NiceColor.BLUE).widthDip(YogaEdge.ALL,5).dashEffect(new float[]{10f,5f},0f).build()).build();
}
