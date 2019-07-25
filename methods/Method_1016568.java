/** 
 * Returns the FormatterStep (whose state will be calculated lazily). 
 */
public FormatterStep build(){
  return FormatterStep.createLazy(formatterName + formatterStepExt,this::get,stateToFormatter);
}
