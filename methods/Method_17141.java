/** 
 * Configures the maximum size. 
 */
void maximumSize(String key,@Nullable String value){
  requireArgument(maximumSize == UNSET_INT,"maximum size was already set to %,d",maximumSize);
  requireArgument(maximumWeight == UNSET_INT,"maximum weight was already set to %,d",maximumWeight);
  maximumSize=parseLong(key,value);
}
