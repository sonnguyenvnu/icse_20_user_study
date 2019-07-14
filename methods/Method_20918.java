public static @NonNull MessageThreadEnvelope messageThreadEnvelope(){
  return MessageThreadEnvelope.builder().messages(Collections.singletonList(MessageFactory.message())).messageThread(MessageThreadFactory.messageThread()).participants(Collections.singletonList(UserFactory.user())).build();
}
