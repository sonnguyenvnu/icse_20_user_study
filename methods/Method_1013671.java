@Override public void register(ConsoleConfigListener consoleConfigListener){
  for (  String key : consoleConfigListener.supportsKeys()) {
    listeners.putIfAbsent(key,new LinkedList<>());
    listeners.get(key).add(consoleConfigListener);
  }
}
