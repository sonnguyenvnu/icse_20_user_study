/** 
 * Create new optin 
 */
@PostMapping("/newsletter") @ApiOperation(httpMethod="POST",value="Creates a newsletter optin",notes="",produces="application/json") @ApiImplicitParams({@ApiImplicitParam(name="store",dataType="string",defaultValue="DEFAULT"),@ApiImplicitParam(name="lang",dataType="string",defaultValue="en")}) public void create(@Valid @RequestBody PersistableCustomerOptin optin,@ApiIgnore MerchantStore merchantStore,@ApiIgnore Language language){
  customerFacade.optinCustomer(optin,merchantStore);
}
