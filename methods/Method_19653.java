@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop(optional=true) boolean canClick,@State Integer count){
  return Text.create(c).text("Clicked " + count + " times.").textSizeDip(50).clickHandler(canClick ? LearningStateComponent.onClick(c) : null).backgroundRes(android.R.color.holo_blue_light).alignSelf(STRETCH).paddingDip(BOTTOM,20).paddingDip(TOP,40).build();
}
