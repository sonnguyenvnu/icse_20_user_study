@RequestMapping(value="/{id}",method=RequestMethod.GET) @ApiOperation(value="Detail",httpMethod="GET",response=ResourceVO.class) @ApiImplicitParams({@ApiImplicitParam(name="Authorization",paramType="header",dataType="string",required=true),@ApiImplicitParam(name="id",paramType="path",dataType="long",required=true)}) public ResponseEntity detail(@ApiIgnore @PathVariable Long id){
  try {
    return new ResponseEntity<>(resourceDomain.getById(id,ResourceVO.class),HttpStatus.OK);
  }
 catch (  Exception e) {
    return resultHelper.errorResp(logger,e,ErrorType.UNKNOWN,e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
