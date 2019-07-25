@GetMapping("/tree") @ApiOperation("???????") public CommonResult<List<AdminsProductCategoryTreeNodeVO>> tree(){
  List<ProductCategoryBO> productCategories=productCategoryService.getAll();
  Map<Integer,AdminsProductCategoryTreeNodeVO> treeNodeMap=productCategories.stream().collect(Collectors.toMap(ProductCategoryBO::getId,ProductCategoryConvert.Admins.INSTANCE::convert));
  treeNodeMap.values().stream().filter(node -> !node.getPid().equals(ProductCategoryConstants.PID_ROOT)).forEach((childNode) -> {
    AdminsProductCategoryTreeNodeVO parentNode=treeNodeMap.get(childNode.getPid());
    if (parentNode.getChildren() == null) {
      parentNode.setChildren(new ArrayList<>());
    }
    parentNode.getChildren().add(childNode);
  }
);
  List<AdminsProductCategoryTreeNodeVO> rootNodes=treeNodeMap.values().stream().filter(node -> node.getPid().equals(ProductCategoryConstants.PID_ROOT)).sorted(Comparator.comparing(AdminsProductCategoryTreeNodeVO::getSort)).collect(Collectors.toList());
  return success(rootNodes);
}
