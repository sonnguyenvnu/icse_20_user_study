@Override public Object toTemplateVariable(final Request request){
  Optional<T> extractContent=extractor.extract(request);
  if (!extractContent.isPresent()) {
    return null;
  }
  T target=extractContent.get();
  if (target instanceof String[]) {
    return target;
  }
  return target.toString();
}
