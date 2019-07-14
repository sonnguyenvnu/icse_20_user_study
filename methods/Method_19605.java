@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Row.create(c).backgroundColor(0xDDFFFFFF).positionType(YogaPositionType.ABSOLUTE).positionDip(YogaEdge.RIGHT,4).positionDip(YogaEdge.TOP,4).paddingDip(YogaEdge.ALL,2).child(FavouriteButton.create(c)).build();
}
