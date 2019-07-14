private static void invokeAll(CopyOnWriteArrayList<ListenerHolder> listeners,ListenerInvocation listenerInvocation){
  for (  ListenerHolder listenerHolder : listeners) {
    listenerHolder.invoke(listenerInvocation);
  }
}
