/** 
 * Returns the binary name of the class corresponding to this type. This method must not be used on method types.
 * @return the binary name of the class corresponding to this type.
 */
public String getClassName(){
switch (sort) {
case VOID:
    return "void";
case BOOLEAN:
  return "boolean";
case CHAR:
return "char";
case BYTE:
return "byte";
case SHORT:
return "short";
case INT:
return "int";
case FLOAT:
return "float";
case LONG:
return "long";
case DOUBLE:
return "double";
case ARRAY:
StringBuilder stringBuilder=new StringBuilder(getElementType().getClassName());
for (int i=getDimensions(); i > 0; --i) {
stringBuilder.append("[]");
}
return stringBuilder.toString();
case OBJECT:
case INTERNAL:
return valueBuffer.substring(valueBegin,valueEnd).replace('/','.');
default :
throw new AssertionError();
}
}
