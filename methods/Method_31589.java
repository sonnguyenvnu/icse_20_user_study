@SuppressWarnings("Duplicates") private String readAdditionalIdentifierParts(PeekingReader reader,char quote,Delimiter delimiter) throws IOException {
  String result="";
  reader.swallow();
  result+=".";
  if (reader.peek(quote)) {
    reader.swallow();
    result+=reader.readUntilExcludingWithEscape(quote,true);
  }
 else {
    result+=reader.readKeywordPart(delimiter);
  }
  if (reader.peek('.')) {
    reader.swallow();
    result+=".";
    if (reader.peek(quote)) {
      reader.swallow();
      result+=reader.readUntilExcludingWithEscape(quote,true);
    }
 else {
      result+=reader.readKeywordPart(delimiter);
    }
  }
  return result;
}
