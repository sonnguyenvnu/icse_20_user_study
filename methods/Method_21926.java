@POST @Path("/connect") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON) public boolean connect(final RegistryCenterConfiguration config,final @Context HttpServletRequest request){
  boolean isConnected=setRegistryCenterNameToSession(regCenterService.find(config.getName(),regCenterService.loadAll()),request.getSession());
  if (isConnected) {
    regCenterService.load(config.getName());
  }
  return isConnected;
}
