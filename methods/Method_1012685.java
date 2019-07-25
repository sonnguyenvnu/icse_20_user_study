/** 
 * Returns  {@code true} if the {@link Set} of type{@link PanasonicDmpProfileType} in {@code deviceProtocolInfo} containsthe specified element.
 * @param protocolInfo the element whose presence is to be tested.
 * @return {@code true} if the {@link Set} of type{@link PanasonicDmpProfileType} in {@code deviceProtocolInfo}contains the specified element,  {@code false} otherwise.
 */
public boolean contains(ProtocolInfo protocolInfo){
  return deviceProtocolInfo.contains(PANASONIC_DMP,protocolInfo);
}
