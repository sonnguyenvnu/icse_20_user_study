@GetMapping({"/login-out","/sign-out","/exit"}) @Authorize @ApiOperation("??????") public ResponseMessage exit(@ApiParam(hidden=true) Authentication authentication){
  eventPublisher.publishEvent(new AuthorizationExitEvent(authentication));
  return ok();
}
