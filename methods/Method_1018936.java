/** 
 * Delegated behaviour pertaining to the associated media subitems.
 * @return subitem behaviour
 */
public SubitemApi subitems(){
  return media != null ? media.subitems() : null;
}
