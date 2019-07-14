@Override public final MessageContent readFor(final Request request){
  return asMessageContent(doReadFor(request));
}
