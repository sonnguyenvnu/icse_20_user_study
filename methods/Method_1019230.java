/** 
 * Perform single PUT on a column-family
 * @param columnFamilyName Column family name
 * @param key Key
 * @param value Payload
 * @param < T > Type of Payload
 */
public <T extends Serializable>void put(String columnFamilyName,String key,T value){
  try {
    byte[] payload=SerializationUtils.serialize(value);
    getRocksDB().put(managedHandlesMap.get(columnFamilyName),key.getBytes(),payload);
  }
 catch (  Exception e) {
    throw new HoodieException(e);
  }
}
