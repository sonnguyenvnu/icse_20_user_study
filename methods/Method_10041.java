/** 
 * Gets an invitecode by the specified invitecode id.
 * @param invitecodeId the specified invitecode id
 * @return for example,      <pre>{ "oId": "", "code": "", "memo": "", .... } </pre>, returns  {@code null} if not found
 */
public JSONObject getInvitecodeById(final String invitecodeId){
  try {
    return invitecodeRepository.get(invitecodeId);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets an invitecode failed",e);
    return null;
  }
}
