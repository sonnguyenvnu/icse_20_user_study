/** 
 * For testing and debugging purposes only where initialization may have not occurred. For any production use, this should never be necessary.
 */
@Override public boolean isInitialized(){
  return mYogaNode != null && mComponentContext != null;
}
