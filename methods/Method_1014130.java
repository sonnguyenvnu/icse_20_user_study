@POST @Path("/{thingUID}/unignore") @ApiOperation(value="Removes ignore flag from a discovery result.") @ApiResponses(value={@ApiResponse(code=200,message="OK")}) public Response unignore(@PathParam("thingUID") @ApiParam(value="thingUID",required=true) String thingUID){
  inbox.setFlag(new ThingUID(thingUID),DiscoveryResultFlag.NEW);
  return Response.ok(null,MediaType.TEXT_PLAIN).build();
}
