/** 
 * Store a new definition for key.  If there was an entry in the table for the key, overwrite it.
 */
public void put(UniqueString key,Object val){
  int loc=key.getDefnLoc();
  if (loc == -1) {
    loc=defnIdx++;
    key.setLoc(loc);
  }
  if (loc >= this.table.length) {
    int oldSize=this.table.length;
    int newSize=Math.max(2 * oldSize,loc + 1);
    Object[] old=this.table;
    this.table=new Object[newSize];
    System.arraycopy(old,0,this.table,0,old.length);
  }
  this.table[loc]=val;
}
