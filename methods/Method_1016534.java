/** 
 * Return the qualified name of the main type declared by this unit.
 * @throws IllegalStateException if no package or type has been declared
 */
public QualifiedName typename(){
  return source.typename();
}
