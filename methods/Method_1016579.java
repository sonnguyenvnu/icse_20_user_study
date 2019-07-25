/** 
 * Uses the specified version of prettier. 
 */
public PrettierConfig prettier(String version){
  return prettier(PrettierFormatterStep.defaultDevDependenciesWithPrettier(version));
}
