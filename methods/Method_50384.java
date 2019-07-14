private List<HmilyCompensationVO> findByPage(final List<String> zNodePaths,final String rootPath,final int start,final int pageSize){
  return zNodePaths.stream().skip(start).limit(pageSize).filter(StringUtils::isNoneBlank).map(zNodePath -> buildByNodePath(rootPath,zNodePath)).collect(Collectors.toList());
}
