/** 
 * Adds a single seqno 
 */
public SeqnoList add(long seqno){
  super.set(index(seqno));
  return this;
}
