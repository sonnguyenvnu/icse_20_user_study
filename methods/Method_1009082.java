/** 
 * Loads the given text in the current sequence depending on the configuration.
 * @param text The W3C DOM text node to load.
 * @throws LoadingException If thrown while parsing.
 */
private void load(Text text) throws LoadingException {
  List<TextEvent> events=this.tokenizer.tokenize(text.getData());
  for (  TextEvent e : events) {
    this.sequence.addEvent(e);
  }
  this.currentWeight+=events.size();
}
