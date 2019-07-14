/** 
 * Whether the <code>StringKeyDirtyFlagMap</code> allows  non-<code>Serializable</code> values.
 * @deprecated JDBCJobStores no longer prune out transient data.  If youinclude non-Serializable values in the Map, you will now get an  exception when attempting to store it in a database.
 */
public boolean getAllowsTransientData(){
  return allowsTransientData;
}
