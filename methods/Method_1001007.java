/** 
 * <p> Item must be added before it can be set or get.
 * @param pID Item name.
 * @param pObject Item value container.
 */
public void add(String pID,MonitoredObject pObject){
  fValues.put(pID,pObject);
}
