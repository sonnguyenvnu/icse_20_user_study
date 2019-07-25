/** 
 * Processes the given node list and returns the corresponding event sequence. <p>This method only returns the event sequence from the first node in the node list, if the node list is empty, this method returns an empty sequence.
 * @param node The W3C DOM node to be processed.
 * @return The recorded sequence of events.
 * @throws LoadingException If thrown while parsing.
 */
public EventSequence process(NodeList node) throws LoadingException {
  if (node.getLength() == 0)   return new EventSequence();
  return process(node.item(0));
}
