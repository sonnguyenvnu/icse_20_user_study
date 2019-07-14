/** 
 * Is generic response boolean.
 * @param serializeType the serialize type
 * @return the boolean
 */
protected boolean isGenericResponse(String serializeType){
  return serializeType != null && serializeType.equals(RemotingConstants.SERIALIZE_FACTORY_GENERIC);
}
