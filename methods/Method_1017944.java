public void subscribe(JmsSendListener listener){
  this.listeners.computeIfAbsent(listener.getDestination(),(d) -> new HashSet<JmsSendListener>()).add(listener);
}
