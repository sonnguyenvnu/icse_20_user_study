@Override public List<DeptVO> tree(String tenantCode){
  return ForestNodeMerger.merge(baseMapper.tree(tenantCode));
}
