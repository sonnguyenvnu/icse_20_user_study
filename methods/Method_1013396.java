/** 
 * @param var The SymbolNode to lookup
 * @param cutoff Iff true, lookup stops at a branching Context. Follows complete chain if false.
 * @return value associated with the {@link SymbolNode} var or null if varcould not be found in the search along the Context "chain"
 */
public final Object lookup(final SymbolNode var,final boolean cutoff){
  Context cur=this;
  while (cur != Empty) {
    if (cur.name != null) {
      if (var == cur.name) {
        return cur.value;
      }
    }
 else     if (cutoff == true) {
      assert cur.value == null;
      return null;
    }
    cur=cur.next;
  }
  return null;
}
