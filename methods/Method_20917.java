public static @NonNull MessageThreadEnvelope empty(){
  return messageThreadEnvelope().toBuilder().messages(null).build();
}
