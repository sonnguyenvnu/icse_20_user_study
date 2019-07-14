/** 
 * Returns the size of values of this type. This method must not be used for method types.
 * @return the size of values of this type, i.e., 2 for {@code long} and {@code double}, 0 for {@code void} and 1 otherwise.
 */
public int getSize(){
switch (sort) {
case VOID:
    return 0;
case BOOLEAN:
case CHAR:
case BYTE:
case SHORT:
case INT:
case FLOAT:
case ARRAY:
case OBJECT:
case INTERNAL:
  return 1;
case LONG:
case DOUBLE:
return 2;
default :
throw new AssertionError();
}
}
