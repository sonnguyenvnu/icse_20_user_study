SpanContext extract(Map<String,String> carrier) throws SpanContextParseException {
  return textFormat.extract(carrier,getter);
}
