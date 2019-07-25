/** 
 * Encodes a boolean value to the byte array.
 * @param target The byte array to encode to.
 * @param idx The starting index in the byte array.
 * @param value The value to encode.
 */
public static void bool(byte[] target,int idx,boolean value){
  target[idx]=value ? (byte)1 : (byte)0;
}
