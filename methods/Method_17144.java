/** 
 * Configures the value as weak or soft references. 
 */
void valueStrength(String key,@Nullable String value,Strength strength){
  requireArgument(value == null,"%s does not take a value",key);
  requireArgument(valueStrength == null,"%s was already set to %s",key,valueStrength);
  valueStrength=strength;
}
