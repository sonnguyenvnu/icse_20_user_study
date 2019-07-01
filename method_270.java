/** 
 * Enable/disable group journal force writes.
 * @param enabled flag to enable/disable group journal force writes
 */
public ServerConfiguration _XXXXX_(boolean enabled){
  setProperty(JOURNAL_ADAPTIVE_GROUP_WRITES,enabled);
  return this;
}