@POST @Path("/{thingUID}/approve") @Consumes(MediaType.TEXT_PLAIN) @ApiOperation(value="Approves the discovery result by adding the thing to the registry.") @ApiResponses(value={@ApiResponse(code=200,message="OK"),@ApiResponse(code=404,message="Thing unable to be approved."),@ApiResponse(code=409,message="No binding found that supports this thing.")}) public Response approve(@HeaderParam(HttpHeaders.ACCEPT_LANGUAGE) @ApiParam(value="language") String language,@PathParam("thingUID") @ApiParam(value="thingUID",required=true) String thingUID,@ApiParam(value="thing label") String label){
  ThingUID thingUIDObject=new ThingUID(thingUID);
  String notEmptyLabel=label != null && !label.isEmpty() ? label : null;
  Thing thing=null;
  try {
    thing=inbox.approve(thingUIDObject,notEmptyLabel);
  }
 catch (  IllegalArgumentException e) {
    logger.error("Thing {} unable to be approved: {}",thingUID,e.getLocalizedMessage());
    return JSONResponse.createErrorResponse(Status.NOT_FOUND,"Thing unable to be approved.");
  }
  if (thing == null) {
    return JSONResponse.createErrorResponse(Status.CONFLICT,"No binding found that can create the thing");
  }
  return Response.ok(null,MediaType.TEXT_PLAIN).build();
}
