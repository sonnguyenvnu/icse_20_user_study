/** 
 * Returns <code>true</code> if the specified event sequence is the same as this one.
 * @param seq The sequence of events to compare with this one.
 * @return <code>true</code> if the specified event sequence is equal to this one;<code>false</code> otherwise.
 */
public boolean equals(EventSequence seq){
  if (seq == null)   return false;
  if (seq.getClass() != this.getClass())   return false;
  List<DiffXEvent> sequence2=seq.sequence;
  if (this.sequence.size() != this.sequence.size())   return false;
  DiffXEvent x1=null;
  DiffXEvent x2=null;
  for (int i=0; i < this.sequence.size(); i++) {
    x1=this.sequence.get(i);
    x2=sequence2.get(i);
    if (!x1.equals(x2))     return false;
  }
  return true;
}
