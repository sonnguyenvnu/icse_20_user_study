/** 
 * Processes the given node and returns the corresponding event sequence.
 * @param node The W3C DOM node to be processed.
 * @return The recorded sequence of events.
 * @throws LoadingException If thrown while parsing.
 */
public EventSequence process(Node node) throws LoadingException {
  this.efactory=new EventFactory(this.config.isNamespaceAware());
  this.tokenizer=TokenizerFactory.get(this.config);
  this.sequence=new EventSequence();
  this.mapping=this.sequence.getPrefixMapping();
  loadNode(node);
  this.isFragment=true;
  return this.sequence;
}
