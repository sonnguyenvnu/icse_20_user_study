@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop(resType=STRING) String title){
  return Text.create(c).text(title).textStyle(BOLD).textSizeDip(24).backgroundColor(0xDDFFFFFF).positionType(YogaPositionType.ABSOLUTE).positionDip(YogaEdge.BOTTOM,4).positionDip(YogaEdge.LEFT,4).paddingDip(YogaEdge.HORIZONTAL,6).build();
}
