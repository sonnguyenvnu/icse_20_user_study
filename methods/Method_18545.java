@Override public @Nullable EventHandler<ClickEvent> getClickHandler(){
  return (mOtherFlags & OFLAG_HAS_CLICK_HANDLER) != 0 ? (EventHandler<ClickEvent>)getOrCreateObjectProps().get(INDEX_ClickHandler) : null;
}
