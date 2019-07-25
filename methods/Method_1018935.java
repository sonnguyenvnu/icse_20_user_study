/** 
 * Delegated behaviour pertaining to media slaves for the associated media.
 * @return media slave behaviour
 */
public SlaveApi slaves(){
  return media != null ? media.slaves() : null;
}
