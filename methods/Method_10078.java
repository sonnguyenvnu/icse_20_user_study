/** 
 * Gets allow register option value.
 * @return allow register option value, return {@code null} if not found
 */
public String getAllowRegister(){
  try {
    final JSONObject result=optionRepository.get(Option.ID_C_MISC_ALLOW_REGISTER);
    return result.optString(Option.OPTION_VALUE);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets option [allow register] value failed",e);
    return null;
  }
}
