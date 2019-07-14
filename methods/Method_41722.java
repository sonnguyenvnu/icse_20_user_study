/** 
 * Determine whether any values in this Map do not implement  <code>Serializable</code>.  Always returns false if this Map is flagged to not allow transient data.
 * @deprecated JDBCJobStores no longer prune out transient data.  If youinclude non-Serializable values in the Map, you will now get an  exception when attempting to store it in a database.
 */
public boolean containsTransientData(){
  if (!getAllowsTransientData()) {
    return false;
  }
  String[] keys=getKeys();
  for (int i=0; i < keys.length; i++) {
    Object o=super.get(keys[i]);
    if (!(o instanceof Serializable)) {
      return true;
    }
  }
  return false;
}
