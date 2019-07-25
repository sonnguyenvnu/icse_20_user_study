/** 
 * Read the next tag.
 * @return the event type of the next tag
 */
public int next(){
  if (endElement) {
    endElement=false;
    eventType=END_ELEMENT;
    currentToken="";
  }
 else {
    read();
  }
  return eventType;
}
