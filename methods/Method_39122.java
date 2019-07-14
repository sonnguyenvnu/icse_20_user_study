/** 
 * Collects all parameters from target into an array.
 */
public Object[] extractParametersValues(){
  final Object[] values=new Object[targets.length - 1];
  for (int i=1; i < targets.length; i++) {
    values[i - 1]=targets[i].value();
  }
  return values;
}
