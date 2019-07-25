/** 
 * Returns an array of all registered handle IDs. These are the IDs for every registered exception.
 * @return an array of all registered handle IDs
 */
static int[] ids(){
  return Arrays.stream(ElasticsearchExceptionHandle.values()).mapToInt(h -> h.id).toArray();
}
