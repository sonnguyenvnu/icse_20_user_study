/** 
 * Creates a new  {@link DLNAOrgFlagsBuilder} instance which can be usedto  {@link #build} an {@link DLNAOrgFlags} instance.
 * @param isDLNA15 Sets bit 20: dlna-v1.5-flag (DLNA v1.5 versioningflag).
 * @return The {@link DLNAOrgFlagsBuilder} instance.
 */
public static DLNAOrgFlagsBuilder builder(boolean isDLNA15){
  return new DLNAOrgFlagsBuilder(isDLNA15);
}
