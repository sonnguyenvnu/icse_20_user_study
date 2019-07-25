/** 
 * Uses the specified version of prettier. 
 */
@Override public PrettierConfig prettier(String version){
  return prettier(PrettierFormatterStep.defaultDevDependenciesWithPrettier(version));
}
