/** 
 * Prints the characters. {@inheritDoc}
 */
@Override public void characters(char[] ch,int position,int offset){
  if (this.states.peek().equals(EMPTY)) {
    this.states.pop();
    this.writer.print('>');
    this.states.push(HAS_TEXT);
  }
  this.writer.print(new String(ch,position,offset));
}
