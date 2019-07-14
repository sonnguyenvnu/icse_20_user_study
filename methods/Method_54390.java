/** 
 * Unsafe version of  {@link #m_contactPointData}. 
 */
public static B3ContactPointData.Buffer nm_contactPointData(long struct){
  return B3ContactPointData.create(memGetAddress(struct + B3ContactInformation.M_CONTACTPOINTDATA),nm_numContactPoints(struct));
}
