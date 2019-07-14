/** 
 * Returns the abstract type corresponding to the internal name of a class.
 * @param symbolTable the type table to use to lookup and store type {@link Symbol}.
 * @param internalName the internal name of a class. This must <i>not</i> be an array typedescriptor.
 * @return the abstract type value corresponding to the given internal name.
 */
static int getAbstractTypeFromInternalName(final SymbolTable symbolTable,final String internalName){
  return REFERENCE_KIND | symbolTable.addType(internalName);
}
