/** 
 * Unsubscribe a listener from all available broker connections.
 */
@Override @SuppressWarnings("null") public void unsubscribe(MQTTTopicDiscoveryParticipant listener){
  TopicSubscribeMultiConnection multiSubscriber=subscriber.remove(listener);
  if (multiSubscriber != null) {
    multiSubscriber.stop();
  }
}
