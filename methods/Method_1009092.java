/** 
 * {@inheritDoc}
 */
public List<TextEvent> tokenize(CharSequence seq){
  if (seq == null)   return null;
  if (seq.length() == 0)   return Collections.emptyList();
  List<TextEvent> events=new ArrayList<TextEvent>(seq.length());
  Pattern p=Pattern.compile("\\s+");
  Matcher m=p.matcher(seq);
  int index=0;
  while (m.find()) {
    if (index != m.start()) {
      String word=seq.subSequence(index,m.start()).toString();
      events.add(getWordEvent(word));
    }
    if (this.whitespace != WhiteSpaceProcessing.IGNORE) {
      String space=seq.subSequence(m.start(),m.end()).toString();
      events.add(getSpaceEvent(space));
    }
    index=m.end();
  }
  if (index != seq.length()) {
    String word=seq.subSequence(index,seq.length()).toString();
    events.add(getWordEvent(word));
  }
  return events;
}
