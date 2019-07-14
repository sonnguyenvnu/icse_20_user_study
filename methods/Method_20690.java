private @Nullable Observable<Pair<PushNotificationEnvelope,MessageThread>> fetchMessageThreadWithEnvelope(final @NonNull PushNotificationEnvelope envelope){
  final PushNotificationEnvelope.Message message=envelope.message();
  if (message == null) {
    return null;
  }
  final Observable<MessageThread> messageThread=this.client.fetchMessagesForThread(message.messageThreadId()).compose(neverError()).map(MessageThreadEnvelope::messageThread);
  return Observable.just(envelope).compose(combineLatestPair(messageThread));
}
