/** 
 * Request focus to a specific index position with an offset.
 * @param index position to focus on.
 * @param offset
 */
@UiThread void requestFocusWithOffset(int index,int offset){
  if (shouldDispatchRequests()) {
    mTarget.requestFocusWithOffset(index,offset);
    return;
  }
  queueRequest(index,offset);
}
