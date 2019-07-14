@OnCreateLayout static Component onCreateLayout(ComponentContext c,@State int state){
  final boolean redLeft=state == 0 || state == 4 || state == 5;
  final boolean blueLeft=state == 0 || state == 1 || state == 5;
  final boolean greenLeft=state == 0 || state == 1 || state == 2;
  return Column.create(c).child(Column.create(c).alignItems(redLeft ? YogaAlign.FLEX_START : YogaAlign.FLEX_END).child(Row.create(c).heightDip(40).widthDip(40).backgroundColor(Color.parseColor("#ee1111")).transitionKey(TRANSITION_KEY_RED).build())).child(Column.create(c).alignItems(blueLeft ? YogaAlign.FLEX_START : YogaAlign.FLEX_END).child(Row.create(c).heightDip(40).widthDip(40).backgroundColor(Color.parseColor("#1111ee")).transitionKey(TRANSITION_KEY_BLUE).build())).child(Column.create(c).alignItems(greenLeft ? YogaAlign.FLEX_START : YogaAlign.FLEX_END).child(Row.create(c).heightDip(40).widthDip(40).backgroundColor(Color.parseColor("#11ee11")).transitionKey(TRANSITION_KEY_GREEN).build())).clickHandler(OneByOneLeftRightBlocksComponent.onClick(c)).build();
}
