/** 
 * Configures the value as weak or soft references. 
 */
void recordStats(@Nullable String value){
  requireArgument(value == null,"record stats does not take a value");
  requireArgument(!recordStats,"record stats was already set");
  recordStats=true;
}
