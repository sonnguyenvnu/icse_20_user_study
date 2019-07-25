public void subscribe(ClusterServiceMessageReceiver messageReceiver,String... topics){
  Arrays.stream(topics).forEach(topic -> messageReceivers.computeIfAbsent(topic,t -> new ArrayList<>()).add(messageReceiver));
}
