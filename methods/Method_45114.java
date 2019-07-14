public final Optional<InputSource> extractAsInputSource(final Request request,final ContentRequestExtractor extractor){
  Optional<MessageContent> content=extractor.extract(request);
  if (content.isPresent()) {
    return of(new InputSource(new ByteArrayInputStream(content.get().getContent())));
  }
  return absent();
}
