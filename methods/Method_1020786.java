@GetMapping("/{id}") @ApiOperation(value="Detail",httpMethod="GET",response=RoleVO.class) @ApiImplicitParams({@ApiImplicitParam(name="Authorization",paramType="header",dataType="string",required=true),@ApiImplicitParam(name="id",paramType="path",dataType="long",required=true)}) public ResponseEntity detail(@ApiIgnore @PathVariable String id){
  try {
    return new ResponseEntity<>(roleDomain.getById(Long.valueOf(id)),HttpStatus.OK);
  }
 catch (  Exception e) {
    return resultHelper.errorResp(logger,e,ErrorType.UNKNOWN,e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
