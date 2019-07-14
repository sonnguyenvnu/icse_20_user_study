@OnEvent(ClickEvent.class) static void onClick(ComponentContext c,@Prop List<Message> initialMessages,@Param boolean adding){
  ExpandableElementRootComponent.onUpdateListSync(c,adding,initialMessages.size());
}
