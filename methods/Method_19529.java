static Component.Builder createSenderTile(ComponentContext c){
  return Row.create(c).marginDip(YogaEdge.ALL,5).alignSelf(YogaAlign.CENTER).widthDip(55).heightDip(55).flexShrink(0).background(getCircle(c));
}
