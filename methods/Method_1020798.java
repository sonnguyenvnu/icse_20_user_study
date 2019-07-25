@SysLog(serviceId=FisherServiceNameConstants.FISHER_GEN_SERVICE,moduleName=MODULE_NAME,actionName="?????????  ??zip?") @ApiOperation(value="?????????",notes="?????????  ??zip?",httpMethod="POST") @ApiImplicitParam(name="buildConfigDTO",value="???",required=true,dataType="BuildConfigDTO") @PostMapping("/code/build") public void code(@RequestBody BuildConfigDTO buildConfigDTO,HttpServletResponse response) throws IOException {
  byte[] data=sysGenService.genCodeByTableName(buildConfigDTO);
  response.reset();
  response.setHeader("Content-Disposition","attachment; filename=\"code.zip\"");
  response.addHeader("Content-Length","" + data.length);
  response.setContentType("application/octet-stream; charset=UTF-8");
  IOUtils.write(data,response.getOutputStream());
}
