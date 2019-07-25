/** 
 * {@inheritDoc}
 */
public List<TextEvent> tokenize(CharSequence seq){
  if (seq == null)   return null;
  if (seq.length() == 0)   return Collections.emptyList();
  int x=TokenizerUtils.getLeadingWhiteSpace(seq);
  int y=TokenizerUtils.getTrailingWhiteSpace(seq);
  if (x == 0 && y == 0) {
    TextEvent e=new CharactersEvent(seq);
    return Collections.singletonList(e);
  }
  if (x == seq.length()) {
switch (this.whitespace) {
case COMPARE:
      return Collections.singletonList((TextEvent)SpaceEvent.getInstance(seq.toString()));
case PRESERVE:
    return Collections.singletonList((TextEvent)new IgnorableSpaceEvent(seq.toString()));
case IGNORE:
  return Collections.emptyList();
default :
}
TextEvent e=new CharactersEvent(seq);
return Collections.singletonList(e);
}
List<TextEvent> events=null;
switch (this.whitespace) {
case COMPARE:
events=new ArrayList<TextEvent>(1 + (x > 0 ? 1 : 0) + (y > 0 ? 1 : 0));
if (x > 0) {
events.add(SpaceEvent.getInstance(seq.subSequence(0,x)));
}
events.add(new CharactersEvent(seq.subSequence(x,seq.length() - y)));
if (y > 0) {
events.add(SpaceEvent.getInstance(seq.subSequence(seq.length() - y,seq.length())));
}
break;
case PRESERVE:
events=new ArrayList<TextEvent>(1 + (x > 0 ? 1 : 0) + (y > 0 ? 1 : 0));
if (x > 0) {
events.add(new IgnorableSpaceEvent(seq.subSequence(0,x)));
}
events.add(new CharactersEvent(seq.subSequence(x,seq.length() - y)));
if (y > 0) {
events.add(new IgnorableSpaceEvent(seq.subSequence(seq.length() - y,seq.length())));
}
break;
case IGNORE:
TextEvent e=new CharactersEvent(seq.subSequence(x,seq.length() - y));
events=Collections.singletonList(e);
break;
default :
}
return events;
}
