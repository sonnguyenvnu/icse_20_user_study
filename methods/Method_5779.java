/** 
 * Scans all of the cached spans in the in-memory representation, removing any for which files no longer exist.
 */
private void removeStaleSpans(){
  ArrayList<CacheSpan> spansToBeRemoved=new ArrayList<>();
  for (  CachedContent cachedContent : index.getAll()) {
    for (    CacheSpan span : cachedContent.getSpans()) {
      if (!span.file.exists()) {
        spansToBeRemoved.add(span);
      }
    }
  }
  for (int i=0; i < spansToBeRemoved.size(); i++) {
    removeSpanInternal(spansToBeRemoved.get(i));
  }
}
