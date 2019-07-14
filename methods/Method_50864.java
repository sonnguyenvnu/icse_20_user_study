/** 
 * {@link #toConfiguration()}.
 * @deprecated To be removed in 7.0.0. Use the instance method {@link #toConfiguration()}.
 */
@Deprecated public static PMDConfiguration transformParametersIntoConfiguration(PMDParameters params){
  return params.toConfiguration();
}
