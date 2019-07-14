@OnEvent(HideItemEvent.class) public static void onHideItem(SectionContext c,@FromEvent Object model,@Prop EventHandler<GetUniqueIdentifierEvent> getUniqueIdentifierHandler){
  HideableDataDiffSection.onBlacklistUpdateSync(c,model,getUniqueIdentifierHandler);
}
