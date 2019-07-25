public static List<BatchItem> from(final String events){
  final List<BatchItem> batch=new ArrayList<>();
  int objectStart=locateOpenSquareBracket(events) + 1;
  final int arrayEnd=locateClosingSquareBracket(objectStart,events);
  while (-1 != (objectStart=navigateToObjectStart(objectStart,arrayEnd,events))) {
    final int objectEnd=navigateToObjectEnd(objectStart,arrayEnd,events,batch::add);
    if (objectEnd == -1) {
      throw new JSONException("Unclosed object staring at " + objectStart + " found.");
    }
    objectStart=objectEnd + 1;
  }
  return batch;
}
