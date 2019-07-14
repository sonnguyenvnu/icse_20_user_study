/** 
 * ??????
 * @param processDefinitionId ????ID
 * @param resourceName        ????
 */
@GetMapping(value="/{processDefinitionId}/resource/{resourceName}") @ApiOperation("??????") @Authorize(action=Permission.ACTION_QUERY) @SneakyThrows public void readResource(@PathVariable String processDefinitionId,@PathVariable String resourceName,HttpServletResponse response){
  ProcessDefinitionQuery pdq=repositoryService.createProcessDefinitionQuery();
  ProcessDefinition pd=pdq.processDefinitionId(processDefinitionId).singleResult();
  try (InputStream resourceAsStream=repositoryService.getResourceAsStream(pd.getDeploymentId(),resourceName)){
    StreamUtils.copy(resourceAsStream,response.getOutputStream());
  }
 }
