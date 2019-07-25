/** 
 * @return name source with {@link #getNewName()} value of the current source as its {@link #getActualName()}.
 */
public VariableNameSource next(){
  return new VariableNameSource(myBaseName,myCounter,getNewName());
}
