/** 
 * Returns the abstract type corresponding to the given type descriptor.
 * @param symbolTable the type table to use to lookup and store type {@link Symbol}.
 * @param buffer a string ending with a type descriptor.
 * @param offset the start offset of the type descriptor in buffer.
 * @return the abstract type corresponding to the given type descriptor.
 */
private static int getAbstractTypeFromDescriptor(final SymbolTable symbolTable,final String buffer,final int offset){
  String internalName;
switch (buffer.charAt(offset)) {
case 'V':
    return 0;
case 'Z':
case 'C':
case 'B':
case 'S':
case 'I':
  return INTEGER;
case 'F':
return FLOAT;
case 'J':
return LONG;
case 'D':
return DOUBLE;
case 'L':
internalName=buffer.substring(offset + 1,buffer.length() - 1);
return REFERENCE_KIND | symbolTable.addType(internalName);
case '[':
int elementDescriptorOffset=offset + 1;
while (buffer.charAt(elementDescriptorOffset) == '[') {
++elementDescriptorOffset;
}
int typeValue;
switch (buffer.charAt(elementDescriptorOffset)) {
case 'Z':
typeValue=BOOLEAN;
break;
case 'C':
typeValue=CHAR;
break;
case 'B':
typeValue=BYTE;
break;
case 'S':
typeValue=SHORT;
break;
case 'I':
typeValue=INTEGER;
break;
case 'F':
typeValue=FLOAT;
break;
case 'J':
typeValue=LONG;
break;
case 'D':
typeValue=DOUBLE;
break;
case 'L':
internalName=buffer.substring(elementDescriptorOffset + 1,buffer.length() - 1);
typeValue=REFERENCE_KIND | symbolTable.addType(internalName);
break;
default :
throw new IllegalArgumentException();
}
return ((elementDescriptorOffset - offset) << DIM_SHIFT) | typeValue;
default :
throw new IllegalArgumentException();
}
}
