/** 
 * Returns type-name to type char. Arrays are not supported.
 */
public static char typeNameToOpcode(final String typeName){
switch (typeName) {
case "byte":
    return 'B';
case "char":
  return 'C';
case "double":
return 'D';
case "float":
return 'F';
case "int":
return 'I';
case "long":
return 'J';
case "short":
return 'S';
case "boolean":
return 'Z';
case "void":
return 'V';
default :
return 'L';
}
}
