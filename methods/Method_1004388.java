@Override public void push(List<PulledMessage> messages){
  for (int i=0; i < messages.size(); i++) {
    final PulledMessage message=messages.get(i);
    if (!push(message)) {
      messageBuffer.addAll(messages.subList(i,messages.size()));
      break;
    }
  }
}
