/** 
 * Creates a  {@code TypescriptFormatExtension} using exactly the specified npm packages. 
 */
public TypescriptFormatExtension tsfmt(Map<String,String> devDependencies){
  TypescriptFormatExtension tsfmt=new TypescriptFormatExtension(devDependencies);
  addStep(tsfmt.createStep());
  return tsfmt;
}
