/** 
 * ??????????.
 * @param request HTTP??
 * @return ????????
 */
@GET @Produces(MediaType.APPLICATION_JSON) public Collection<RegistryCenterConfiguration> load(final @Context HttpServletRequest request){
  Optional<RegistryCenterConfiguration> regCenterConfig=regCenterService.loadActivated();
  if (regCenterConfig.isPresent()) {
    setRegistryCenterNameToSession(regCenterConfig.get(),request.getSession());
  }
  return regCenterService.loadAll().getRegistryCenterConfiguration();
}
