/** 
 * Whether the given field is a serialPersistentFields variable. <p/> This field must be initialized with an array of ObjectStreamField objects. The modifiers for the field are required to be private, static, and final.
 * @see <a href="https://docs.oracle.com/javase/7/docs/platform/serialization/spec/serial-arch.html#6250">Oracle docs</a>
 * @param field the field, must not be null
 * @return true if the field ia a serialPersistentFields variable, otherwise false
 */
private boolean isSerialPersistentFields(final ASTFieldDeclaration field){
  return "serialPersistentFields".equals(field.getVariableName()) && field.isPrivate() && field.isStatic() && field.isFinal() && field.isArray() && "ObjectStreamField".equals(field.jjtGetFirstToken().getImage());
}
