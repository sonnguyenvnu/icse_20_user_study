/** 
 * Sets the ith element of given table, with volatile write semantics. (See above about use of putOrderedObject.)
 */
static final <K,V>void setEntryAt(HashEntry<K,V>[] tab,int i,HashEntry<K,V> e){
  UNSAFE.putOrderedObject(tab,((long)i << TSHIFT) + TBASE,e);
}
