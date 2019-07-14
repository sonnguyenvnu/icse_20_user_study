/** 
 * NOTE new API, use this to replace  {@link BaseTangramEngine#appendData(List)} and {@link BaseTangramEngine#appendData(Object)}
 * @param groups new groups to be append at tail.
 * @since 2.1.0
 */
public void appendBatchWith(List<Card> groups){
  if (mGroupBasicAdapter != null) {
    insertBatchWith(mGroupBasicAdapter.getGroups().size(),groups);
  }
}
