/** 
 * Returns the total number of bytes of the message batch (by calling  {@link org.jgroups.Message#getLength()} on all messages) 
 */
public int length(){
  int retval=0;
  for (int i=0; i < index; i++)   retval+=length_visitor.applyAsInt(messages[i],this);
  return retval;
}
