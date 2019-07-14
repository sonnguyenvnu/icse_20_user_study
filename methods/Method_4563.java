@Override protected void parsePayload(ParsableByteArray data,long timeUs) throws ParserException {
  int nameType=readAmfType(data);
  if (nameType != AMF_TYPE_STRING) {
    throw new ParserException();
  }
  String name=readAmfString(data);
  if (!NAME_METADATA.equals(name)) {
    return;
  }
  int type=readAmfType(data);
  if (type != AMF_TYPE_ECMA_ARRAY) {
    return;
  }
  Map<String,Object> metadata=readAmfEcmaArray(data);
  if (metadata.containsKey(KEY_DURATION)) {
    double durationSeconds=(double)metadata.get(KEY_DURATION);
    if (durationSeconds > 0.0) {
      durationUs=(long)(durationSeconds * C.MICROS_PER_SECOND);
    }
  }
}
