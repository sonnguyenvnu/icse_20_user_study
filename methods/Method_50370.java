@Override public CommonPager<HmilyCompensationVO> listByPage(final CompensationQuery query){
  final String filePath=RepositoryPathUtils.buildFilePath(query.getApplicationName());
  final PageParameter pageParameter=query.getPageParameter();
  final int currentPage=pageParameter.getCurrentPage();
  final int pageSize=pageParameter.getPageSize();
  int start=(currentPage - 1) * pageSize;
  CommonPager<HmilyCompensationVO> voCommonPager=new CommonPager<>();
  File path;
  File[] files;
  int totalCount;
  List<HmilyCompensationVO> voList;
  if (StringUtils.isBlank(query.getTransId()) && Objects.nonNull(query.getRetry())) {
    path=new File(filePath);
    files=path.listFiles();
    final List<HmilyCompensationVO> all=findAll(files);
    if (CollectionUtils.isNotEmpty(all)) {
      final List<HmilyCompensationVO> collect=all.stream().filter(Objects::nonNull).filter(vo -> vo.getRetriedCount() < query.getRetry()).collect(Collectors.toList());
      totalCount=collect.size();
      voList=collect.stream().skip(start).limit(pageSize).collect(Collectors.toList());
    }
 else {
      totalCount=0;
      voList=null;
    }
  }
 else   if (StringUtils.isNoneBlank(query.getTransId()) && Objects.isNull(query.getRetry())) {
    final String fullFileName=RepositoryPathUtils.getFullFileName(filePath,query.getTransId());
    final File file=new File(fullFileName);
    files=new File[]{file};
    totalCount=files.length;
    voList=findAll(files);
  }
 else   if (StringUtils.isNoneBlank(query.getTransId()) && Objects.nonNull(query.getRetry())) {
    final String fullFileName=RepositoryPathUtils.getFullFileName(filePath,query.getTransId());
    final File file=new File(fullFileName);
    files=new File[]{file};
    totalCount=files.length;
    voList=findAll(files).stream().filter(Objects::nonNull).filter(vo -> vo.getRetriedCount() < query.getRetry()).collect(Collectors.toList());
  }
 else {
    path=new File(filePath);
    files=path.listFiles();
    totalCount=Objects.requireNonNull(files).length;
    voList=findByPage(files,start,pageSize);
  }
  voCommonPager.setPage(PageHelper.buildPage(query.getPageParameter(),totalCount));
  voCommonPager.setDataList(voList);
  return voCommonPager;
}
