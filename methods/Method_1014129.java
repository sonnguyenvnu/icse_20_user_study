@POST @Path("/{thingUID}/ignore") @ApiOperation(value="Flags a discovery result as ignored for further processing.") @ApiResponses(value={@ApiResponse(code=200,message="OK")}) public Response ignore(@PathParam("thingUID") @ApiParam(value="thingUID",required=true) String thingUID){
  inbox.setFlag(new ThingUID(thingUID),DiscoveryResultFlag.IGNORED);
  return Response.ok(null,MediaType.TEXT_PLAIN).build();
}
