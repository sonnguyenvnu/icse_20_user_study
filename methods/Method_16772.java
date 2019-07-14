@PostMapping("/{modelId}/deploy") @ApiOperation("????") @Authorize(action="deploy") public ResponseMessage<Deployment> deployModel(@PathVariable String modelId) throws Exception {
  Model modelData=repositoryService.getModel(modelId);
  if (modelData == null) {
    throw new NotFoundException("?????!");
  }
  ObjectNode modelNode=(ObjectNode)new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
  BpmnModel model=new BpmnJsonConverter().convertToBpmnModel(modelNode);
  byte[] bpmnBytes=new BpmnXMLConverter().convertToXML(model);
  String processName=modelData.getName() + ".bpmn20.xml";
  Deployment deployment=repositoryService.createDeployment().name(modelData.getName()).addString(processName,new String(bpmnBytes,"utf8")).deploy();
  return ResponseMessage.ok(deployment).include(Deployment.class,"id","name","new");
}
