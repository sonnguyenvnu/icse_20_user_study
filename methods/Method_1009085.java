/** 
 * Loads the given attribute in the current sequence.
 * @param e An attribute event.
 */
private void load(AttributeEvent e){
  if ("http://www.w3.org/2000/xmlns/".equals(e.getURI())) {
    this.sequence.mapPrefix(e.getValue(),e.getName());
  }
 else {
    e.setWeight(2);
    this.currentWeight+=2;
    this.sequence.addEvent(e);
  }
}
