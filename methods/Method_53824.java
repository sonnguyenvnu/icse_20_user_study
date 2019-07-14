/** 
 * Sets the address of the specified encoded string to the  {@code description} field. 
 */
public AIExportFormatDesc description(@NativeType("char const *") ByteBuffer value){
  ndescription(address(),value);
  return this;
}
