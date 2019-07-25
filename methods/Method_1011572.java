/** 
 * Factory may get invalidated independent from ConfigurationType (i.e. if the latter is defined in another, non-reloaded plugin)
 */
void invalidate(){
  myIsIvalid=true;
}
