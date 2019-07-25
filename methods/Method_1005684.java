/** 
 * Declares a field.
 * @param flags a bitwise combination of {@link Modifier#PUBLIC},  {@link Modifier#PRIVATE},  {@link Modifier#PROTECTED},  {@link Modifier#STATIC}, {@link Modifier#FINAL},  {@link Modifier#VOLATILE}, and  {@link Modifier#TRANSIENT}.
 * @param staticValue a constant representing the initial value for thestatic field, possibly null. This must be null if this field is non-static.
 */
public void declare(FieldId<?,?> fieldId,int flags,Object staticValue){
  TypeDeclaration typeDeclaration=getTypeDeclaration(fieldId.declaringType);
  if (typeDeclaration.fields.containsKey(fieldId)) {
    throw new IllegalStateException("already declared: " + fieldId);
  }
  int supportedFlags=Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED | Modifier.STATIC | Modifier.FINAL | Modifier.VOLATILE | Modifier.TRANSIENT | AccessFlags.ACC_SYNTHETIC;
  if ((flags & ~supportedFlags) != 0) {
    throw new IllegalArgumentException("Unexpected flag: " + Integer.toHexString(flags));
  }
  if ((flags & Modifier.STATIC) == 0 && staticValue != null) {
    throw new IllegalArgumentException("staticValue is non-null, but field is not static");
  }
  FieldDeclaration fieldDeclaration=new FieldDeclaration(fieldId,flags,staticValue);
  typeDeclaration.fields.put(fieldId,fieldDeclaration);
}
