/** 
 * Defines many parameters at once.
 */
public void defineParameters(final Map<?,?> properties){
  for (  Map.Entry<?,?> entry : properties.entrySet()) {
    defineParameter(entry.getKey().toString(),entry.getValue());
  }
}
