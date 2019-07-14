@Override protected Optional<String[]> doExtract(final HttpRequest request){
  String[] extractedValues=from(request.getHeaders().entrySet()).filter(isForHeaderName(name)).transform(toValue()).transformAndConcat(arrayAsIterable()).toArray(String.class);
  if (extractedValues.length > 0) {
    return of(extractedValues);
  }
  return absent();
}
