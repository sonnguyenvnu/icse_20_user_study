/** 
 * Validates if this  {@link DLNAOrgOperations} instance can be used in agiven  {@link ProtocolInfo} instance without breaking DLNA rules.<p> <b>Note:</b> Currently  {@code res@size} and {@code res@duration} isn'timplemented in  {@link ProtocolInfo}, which means that the related requirements can't be verified.
 * @param protocolInfo the {@link ProtocolInfo} instance to verify for.
 * @return {@code true} if the validation succeeded, false otherwise.
 */
public boolean validate(ProtocolInfo protocolInfo){
  return validate(protocolInfo.getFlags());
}
