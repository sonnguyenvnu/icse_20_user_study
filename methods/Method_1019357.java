@SuppressWarnings("Duplicates") @GetMapping("/tree") @ApiOperation(value="???????????????") public CommonResult<List<ResourceTreeNodeVO>> tree(){
  List<ResourceBO> resources=resourceService.getResourcesByType(null);
  Map<Integer,ResourceTreeNodeVO> treeNodeMap=resources.stream().collect(Collectors.toMap(ResourceBO::getId,ResourceConvert.INSTANCE::convert2));
  treeNodeMap.values().stream().filter(node -> !node.getPid().equals(ResourceConstants.PID_ROOT)).forEach((childNode) -> {
    ResourceTreeNodeVO parentNode=treeNodeMap.get(childNode.getPid());
    if (parentNode.getChildren() == null) {
      parentNode.setChildren(new ArrayList<>());
    }
    parentNode.getChildren().add(childNode);
  }
);
  List<ResourceTreeNodeVO> rootNodes=treeNodeMap.values().stream().filter(node -> node.getPid().equals(ResourceConstants.PID_ROOT)).sorted(Comparator.comparing(ResourceTreeNodeVO::getSort)).collect(Collectors.toList());
  return success(rootNodes);
}
