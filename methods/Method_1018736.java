/** 
 * Returns the bytes that represent an internal SQL Server format of Geometry type.
 * @return byte array representation of the Geometry object.
 */
public byte[] serialize(){
  return wkb;
}
