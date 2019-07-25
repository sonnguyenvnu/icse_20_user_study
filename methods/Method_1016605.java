/** 
 * Uses exactly the npm packages specified in the map. 
 */
@Override public PrettierConfig prettier(Map<String,String> devDependencies){
  PrettierConfig prettierConfig=new TypescriptPrettierConfig(devDependencies);
  addStep(prettierConfig.createStep());
  return prettierConfig;
}
