/** 
 * Loads the given processing instruction in the current sequence.
 * @param pi The W3C DOM PI node to load.
 * @throws LoadingException If thrown while parsing.
 */
private void load(ProcessingInstruction pi) throws LoadingException {
  this.sequence.addEvent(new ProcessingInstructionEvent(pi.getTarget(),pi.getData()));
  this.currentWeight++;
}
