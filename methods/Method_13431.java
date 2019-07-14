/** 
 * Get the best matched  {@link RequestMetadata} via specified {@link RequestMetadata}
 * @param requestMetadataMap the source of  {@link NavigableMap}
 * @param requestMetadata    the match object
 * @return if not matched, return <code>null</code>
 */
public static RequestMetadata getBestMatch(NavigableMap<RequestMetadata,RequestMetadata> requestMetadataMap,RequestMetadata requestMetadata){
  RequestMetadata key=requestMetadata;
  RequestMetadata result=requestMetadataMap.get(key);
  if (result == null) {
    SortedMap<RequestMetadata,RequestMetadata> headMap=requestMetadataMap.headMap(key,true);
    result=headMap.isEmpty() ? null : requestMetadataMap.get(headMap.lastKey());
  }
  return result;
}
