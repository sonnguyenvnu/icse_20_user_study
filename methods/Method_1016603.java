/** 
 * Uses the default version of prettier. 
 */
@Override public PrettierConfig prettier(){
  return prettier(PrettierFormatterStep.defaultDevDependencies());
}
