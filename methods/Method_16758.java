@GetMapping(value={"/process-instance/{processInstanceId}/diagram-layout"},produces=MediaType.APPLICATION_JSON_UTF8_VALUE) public Object getDiagram(@PathVariable String processInstanceId){
  return this.getDiagramNode(processInstanceId,null);
}
