/** 
 * Removes dot segments from the path of a URI.
 * @param uri A {@link StringBuilder} containing the URI.
 * @param offset The index of the start of the path in {@code uri}.
 * @param limit The limit (exclusive) of the path in {@code uri}.
 */
private static String removeDotSegments(StringBuilder uri,int offset,int limit){
  if (offset >= limit) {
    return uri.toString();
  }
  if (uri.charAt(offset) == '/') {
    offset++;
  }
  int segmentStart=offset;
  int i=offset;
  while (i <= limit) {
    int nextSegmentStart;
    if (i == limit) {
      nextSegmentStart=i;
    }
 else     if (uri.charAt(i) == '/') {
      nextSegmentStart=i + 1;
    }
 else {
      i++;
      continue;
    }
    if (i == segmentStart + 1 && uri.charAt(segmentStart) == '.') {
      uri.delete(segmentStart,nextSegmentStart);
      limit-=nextSegmentStart - segmentStart;
      i=segmentStart;
    }
 else     if (i == segmentStart + 2 && uri.charAt(segmentStart) == '.' && uri.charAt(segmentStart + 1) == '.') {
      int prevSegmentStart=uri.lastIndexOf("/",segmentStart - 2) + 1;
      int removeFrom=prevSegmentStart > offset ? prevSegmentStart : offset;
      uri.delete(removeFrom,nextSegmentStart);
      limit-=nextSegmentStart - removeFrom;
      segmentStart=prevSegmentStart;
      i=prevSegmentStart;
    }
 else {
      i++;
      segmentStart=i;
    }
  }
  return uri.toString();
}
