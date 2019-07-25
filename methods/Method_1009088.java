/** 
 * {@inheritDoc}
 */
public List<TextEvent> tokenize(CharSequence seq){
  if (seq == null)   return null;
  if (seq.length() == 0)   return Collections.emptyList();
  List<TextEvent> events=new ArrayList<TextEvent>(seq.length());
  Character c=null;
  for (int i=0; i < seq.length(); i++) {
    c=Character.valueOf(seq.charAt(i));
    TextEvent e=this.recycling.get(c);
    if (e == null) {
      if (Character.isWhitespace(c.charValue())) {
        e=SpaceEvent.getInstance(c);
      }
 else {
        e=new CharactersEvent(c + "");
      }
    }
    events.add(e);
  }
  return events;
}
