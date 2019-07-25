/** 
 * Replaces a message in the batch with another one
 * @param existing_msg The message to be replaced. The message has to be non-null and is found by identity (==)comparison
 * @param new_msg The message to replace the existing message with, can be null
 * @return
 */
public MessageBatch replace(Message existing_msg,Message new_msg){
  if (existing_msg == null)   return this;
  for (int i=0; i < index; i++) {
    if (messages[i] != null && messages[i] == existing_msg) {
      messages[i]=new_msg;
      break;
    }
  }
  return this;
}
