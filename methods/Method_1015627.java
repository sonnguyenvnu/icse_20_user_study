/** 
 * Called by the default implementation of  {@link #up(org.jgroups.util.MessageBatch)} for each message to determineif the message should be removed from the message batch (and handled by the current protocol) or not.
 * @param msg The message. Guaranteed to be non-null
 * @return True if the message should be handled by this protocol (will be removed from the batch), false if themessage should remain in the batch and be passed up.<p/> The default implementation tries to find a header matching the current protocol's ID and returns true if there is a match, or false otherwise
 */
protected boolean accept(Message msg){
  short tmp_id=getId();
  return tmp_id > 0 && msg.getHeader(tmp_id) != null;
}
