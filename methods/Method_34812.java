/** 
 * @ExcludeFromJavadoc
 */
static boolean isArchaiusV1Available(){
  return LazyHolder.loadCascadedPropertiesFromResources != null;
}
