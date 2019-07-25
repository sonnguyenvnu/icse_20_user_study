public void dispatch(GameEvent event){
  for (  GameEventListener listener : listeners) {
    if (event.getClass().equals(listener.getListenedEventType())) {
      listener.handle(event);
    }
  }
  for (  ServerPlugin plugin : pluginManager.getPlugins()) {
    for (    GameEventListener listener : plugin.getListeners()) {
      if (event.getClass().equals(listener.getListenedEventType())) {
        listener.handle(event);
      }
    }
  }
}
