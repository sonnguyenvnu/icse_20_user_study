@GetMapping(value="/{modelId}/json") @Authorize(action=Permission.ACTION_GET) public Object getEditorJson(@PathVariable String modelId){
  JSONObject modelNode;
  Model model=repositoryService.getModel(modelId);
  if (model == null)   throw new NullPointerException("?????");
  if (StringUtils.isNotEmpty(model.getMetaInfo())) {
    modelNode=JSON.parseObject(model.getMetaInfo());
  }
 else {
    modelNode=new JSONObject();
    modelNode.put(MODEL_NAME,model.getName());
  }
  modelNode.put(MODEL_ID,model.getId());
  modelNode.put("model",JSON.parse(new String(repositoryService.getModelEditorSource(model.getId()))));
  return modelNode;
}
