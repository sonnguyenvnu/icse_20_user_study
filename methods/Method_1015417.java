/** 
 * Adds a new node to the tree and sets its data. If the node doesn not yet exist, it will be created. Also, parent nodes will be created if not existent. If the node already has data, then the new data will override the old one. If the node already existed, a nodeModified() notification will be generated. Otherwise a nodeCreated() motification will be emitted.
 * @param fqn The fully qualified name of the new node
 * @param data The new data. May be null if no data should be set in the node.
 */
public void put(String fqn,HashMap data){
  if (!remote_calls) {
    _put(fqn,data);
    return;
  }
  if (send_message) {
    if (channel == null) {
      if (log.isErrorEnabled())       log.error("channel is null, cannot broadcast PUT request");
      return;
    }
    try {
      channel.send(new Message(null,new Request(Request.PUT,fqn,data)));
    }
 catch (    Exception ex) {
      if (log.isErrorEnabled())       log.error("failure bcasting PUT request: " + ex);
    }
  }
 else {
    _put(fqn,data);
  }
}
