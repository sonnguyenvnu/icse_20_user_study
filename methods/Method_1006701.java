/** 
 * Creates a new user
 * @param store
 * @param user
 * @return
 */
@ResponseStatus(HttpStatus.OK) @PostMapping(value={"/private/{store}/user/","/private/user/"},produces=MediaType.APPLICATION_JSON_VALUE) @ApiOperation(httpMethod="POST",value="Creates a new user",notes="",response=ReadableUser.class) public ReadableUser create(@ApiParam(name="store",value="Optional - Store code",required=false,defaultValue="DEFAULT") @PathVariable Optional<String> store,@Valid @RequestBody PersistableUser user,HttpServletRequest request){
  String authenticatedUser=userFacade.authenticatedUser();
  if (authenticatedUser == null) {
    throw new UnauthorizedException();
  }
  userFacade.authorizedGroup(authenticatedUser,Stream.of("SUPERADMIN","ADMIN").collect(Collectors.toList()));
  String storeCd=Constants.DEFAULT_STORE;
  if (store.isPresent()) {
    storeCd=store.get();
  }
  MerchantStore merchantStore=storeFacade.get(storeCd);
  if (!request.isUserInRole("SUPERADMIN")) {
    userFacade.authorizedStore(authenticatedUser,storeCd);
  }
  return userFacade.create(user,merchantStore);
}
