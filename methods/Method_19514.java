@OnEvent(ClickEvent.class) static void onSecondComponentClick(ComponentContext c){
  BoundsAnimationComponent.toggleFlag2Sync(c);
}
