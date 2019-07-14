/** 
 * Sets the specified value to the  {@code cGreenShift} field. 
 */
public PIXELFORMATDESCRIPTOR cGreenShift(@NativeType("BYTE") byte value){
  ncGreenShift(address(),value);
  return this;
}
