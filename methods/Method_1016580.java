/** 
 * Uses exactly the npm packages specified in the map. 
 */
public PrettierConfig prettier(Map<String,String> devDependencies){
  PrettierConfig prettierConfig=new PrettierConfig(devDependencies);
  addStep(prettierConfig.createStep());
  return prettierConfig;
}
