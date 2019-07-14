/** 
 * Returns an object representation of CEA-708 initialization data
 * @param initializationData Binary CEA-708 initialization data.
 * @return The object representation.
 */
public static Cea708InitializationData fromData(List<byte[]> initializationData){
  return new Cea708InitializationData(initializationData);
}
