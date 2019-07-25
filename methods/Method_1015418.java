/** 
 * Adds a key and value to a given node. If the node doesn't exist, it will be created. If the node already existed, a nodeModified() notification will be generated. Otherwise a nodeCreated() motification will be emitted.
 * @param fqn The fully qualified name of the node
 * @param key The key
 * @param value The value
 */
public void put(String fqn,String key,Object value){
  if (!remote_calls) {
    _put(fqn,key,value);
    return;
  }
  if (send_message) {
    if (channel == null) {
      if (log.isErrorEnabled())       log.error("channel is null, cannot broadcast PUT request");
      return;
    }
    try {
      channel.send(new Message(null,new Request(Request.PUT,fqn,key,value)));
    }
 catch (    Exception ex) {
      if (log.isErrorEnabled())       log.error("failure bcasting PUT request: " + ex);
    }
  }
 else {
    _put(fqn,key,value);
  }
}
