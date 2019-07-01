@DELETE @Path("/{uuid}") @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON) @RolesAllowed({User.Role.USER,User.Role.ADMINISTRATOR}) public RESTResponse<DashboardEntity> _XXXXX_(String uuid,@Auth User user){
  return RESTResponse.async(() -> dashboardEntityService.deleteByUUID(uuid,user)).get();
}