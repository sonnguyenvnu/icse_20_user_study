@DELETE @Path("/{itemName}/{channelUID}") @ApiOperation(value="Unlinks item from a channel.") @ApiResponses(value={@ApiResponse(code=200,message="OK"),@ApiResponse(code=404,message="Link not found."),@ApiResponse(code=405,message="Link not editable.")}) public Response unlink(@PathParam("itemName") @ApiParam(value="itemName") String itemName,@PathParam("channelUID") @ApiParam(value="channelUID") String channelUid){
  String linkId=AbstractLink.getIDFor(itemName,new ChannelUID(channelUid));
  if (itemChannelLinkRegistry.get(linkId) == null) {
    String message="Link " + linkId + " does not exist!";
    return JSONResponse.createResponse(Status.NOT_FOUND,null,message);
  }
  ItemChannelLink result=itemChannelLinkRegistry.remove(AbstractLink.getIDFor(itemName,new ChannelUID(channelUid)));
  if (result != null) {
    return Response.ok(null,MediaType.TEXT_PLAIN).build();
  }
 else {
    return JSONResponse.createErrorResponse(Status.METHOD_NOT_ALLOWED,"Channel is read-only.");
  }
}
