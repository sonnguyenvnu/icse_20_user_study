/** 
 * Sends a message. The message contains <ol> <li>a destination address (Address). A  {@code null} address sends the message to all cluster members.<li>a source address. Can be left empty as it will be assigned automatically <li>a byte buffer. The message contents. <li>several additional fields. They can be used by application programs (or patterns). E.g. a message ID, flags etc </ol>
 * @param msg the message to be sent. Destination and buffer should be set. A null destinationmeans to send to all group members.
 * @exception IllegalStateException thrown if the channel is disconnected or closed
 */
public JChannel send(Message msg) throws Exception {
  if (msg == null)   throw new NullPointerException("msg is null");
  checkClosedOrNotConnected();
  down(msg);
  return this;
}
