/** 
 * Uses the specified version of typescript-format. 
 */
public TypescriptFormatExtension tsfmt(String version){
  return tsfmt(TsFmtFormatterStep.defaultDevDependenciesWithTsFmt(version));
}
