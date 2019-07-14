/** 
 * Adds a simple field, accessor, and mutator for the variable. 
 */
private void addFieldAndGetter(String varName){
  context.nodeSubtype.addField(NODE,varName).addMethod(newGetter(Strength.STRONG,NODE,varName,Visibility.IMMEDIATE)).addMethod(newSetter(NODE,varName,Visibility.IMMEDIATE));
}
