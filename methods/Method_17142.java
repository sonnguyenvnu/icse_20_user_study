/** 
 * Configures the maximum size. 
 */
void maximumWeight(String key,@Nullable String value){
  requireArgument(maximumWeight == UNSET_INT,"maximum weight was already set to %,d",maximumWeight);
  requireArgument(maximumSize == UNSET_INT,"maximum size was already set to %,d",maximumSize);
  maximumWeight=parseLong(key,value);
}
