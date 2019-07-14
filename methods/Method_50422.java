private String buildRepositorySuffix(final String repositorySuffix){
  if (StringUtils.isNoneBlank(repositorySuffix)) {
    return repositorySuffix;
  }
 else {
    return hmilyApplicationService.acquireName();
  }
}
