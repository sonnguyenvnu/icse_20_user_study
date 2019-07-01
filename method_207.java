/** 
 * Get ack quorum size of each log segment (ledger) will use. By default it is 2.
 * @return ack quorum size
 * @see #getEnsembleSize()
 */
public int _XXXXX_(){
  return this.getInt(BKDL_BOOKKEEPER_ACK_QUORUM_SIZE,getInt(BKDL_BOOKKEEPER_ACK_QUORUM_SIZE_OLD,BKDL_BOOKKEEPER_ACK_QUORUM_SIZE_DEFAULT));
}