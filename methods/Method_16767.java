/** 
 * ??????Model
 */
@PutMapping(value="/convert-to-model/{processDefinitionId}") @ApiOperation("????????") @Authorize(action=Permission.ACTION_UPDATE) public ResponseMessage<String> convertToModel(@PathVariable("processDefinitionId") String processDefinitionId) throws UnsupportedEncodingException, XMLStreamException {
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
  if (null == processDefinition) {
    throw new NotFoundException();
  }
  InputStream bpmnStream=repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),processDefinition.getResourceName());
  XMLInputFactory xif=XMLInputFactory.newInstance();
  InputStreamReader in=new InputStreamReader(bpmnStream,"UTF-8");
  XMLStreamReader xtr=xif.createXMLStreamReader(in);
  BpmnModel bpmnModel=new BpmnXMLConverter().convertToBpmnModel(xtr);
  BpmnJsonConverter converter=new BpmnJsonConverter();
  com.fasterxml.jackson.databind.node.ObjectNode modelNode=converter.convertToJson(bpmnModel);
  org.activiti.engine.repository.Model modelData=repositoryService.newModel();
  modelData.setKey(processDefinition.getKey());
  modelData.setName(processDefinition.getResourceName().substring(0,processDefinition.getResourceName().indexOf(".")));
  modelData.setCategory(processDefinition.getDeploymentId());
  ObjectNode modelObjectNode=new ObjectMapper().createObjectNode();
  modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME,processDefinition.getName());
  modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION,1);
  modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,processDefinition.getDescription());
  modelData.setMetaInfo(modelObjectNode.toString());
  repositoryService.saveModel(modelData);
  repositoryService.addModelEditorSource(modelData.getId(),modelNode.toString().getBytes("utf-8"));
  return ResponseMessage.ok(modelData.getId());
}
