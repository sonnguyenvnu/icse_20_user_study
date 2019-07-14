/** 
 * Tells if memory index is completed in initialization. Only call it when you need to know if memory index is completed in cold start.
 */
public boolean isIndexReady(){
  return mIndexReady || !mIndexPopulateAtStartupEnabled;
}
