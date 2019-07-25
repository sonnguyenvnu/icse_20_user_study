/** 
 * Returns the bytes that represent an internal SQL Server format of Geography type.
 * @return byte array representation of the Geography object.
 */
public byte[] serialize(){
  return wkb;
}
