/** 
 * Returns a source excerpt suitable for constructing an instance of this type, including "new" keyword but excluding brackets.
 */
public Excerpt constructor(){
  return Excerpts.add("new %s%s",getQualifiedName(),diamondOperator());
}
