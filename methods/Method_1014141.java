@POST @Path("/interpreters") @Consumes(MediaType.TEXT_PLAIN) @ApiOperation(value="Sends a text to the default human language interpreter.") @ApiResponses(value={@ApiResponse(code=200,message="OK"),@ApiResponse(code=404,message="No human language interpreter was found."),@ApiResponse(code=400,message="interpretation exception occurs")}) public Response interpret(@HeaderParam(HttpHeaders.ACCEPT_LANGUAGE) @ApiParam(value="language") String language,@ApiParam(value="text to interpret",required=true) String text){
  final Locale locale=localeService.getLocale(language);
  HumanLanguageInterpreter hli=voiceManager.getHLI();
  if (hli != null) {
    try {
      hli.interpret(locale,text);
      return Response.ok(null,MediaType.TEXT_PLAIN).build();
    }
 catch (    InterpretationException e) {
      return JSONResponse.createErrorResponse(Status.BAD_REQUEST,e.getMessage());
    }
  }
 else {
    return JSONResponse.createErrorResponse(Status.NOT_FOUND,"No interpreter found");
  }
}
