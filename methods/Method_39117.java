/** 
 * Writes value to this target. Depending on a flag, writing the value can be completely silent, when no exception is thrown and with top performances. Otherwise, an exception is thrown on a failure.
 */
public void writeValue(final InjectionPoint injectionPoint,final Object propertyValue,final boolean silent){
  writeValue(injectionPoint.targetName(),propertyValue,silent);
}
