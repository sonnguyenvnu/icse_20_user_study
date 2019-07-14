@OnEvent(ClickEvent.class) static void onClickSave(ComponentContext c){
  StoryCardComponent.onToggleSavedStateSync(c);
}
