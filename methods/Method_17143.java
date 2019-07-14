/** 
 * Configures the keys as weak references. 
 */
void weakKeys(@Nullable String value){
  requireArgument(value == null,"weak keys does not take a value");
  requireArgument(keyStrength == null,"weak keys was already set");
  keyStrength=Strength.WEAK;
}
