@GET @Path("search") @Produces(MediaType.APPLICATION_JSON) public GenericServiceAPIResponseEntity _XXXXX_(@QueryParam("jobId") String jobId,@QueryParam("jobDefId") String jobDefId,@QueryParam("site") String site){
  GenericServiceAPIResponseEntity response=new GenericServiceAPIResponseEntity();
  if ((jobId == null && jobDefId == null) || site == null) {
    response.setException(new IllegalArgumentException("Error: (jobId == null && jobDefId == null) || site == null"));
    response.setSuccess(false);
    return response;
  }
  List<TaggedLogAPIEntity> jobs=new ArrayList<>();
  Set<String> jobIds=new HashSet<>();
  String condition=buildCondition(jobId,jobDefId,site);
  if (condition == null) {
    response.setException(new Exception("Search condition is empty"));
    response.setSuccess(false);
    return response;
  }
  LOG.debug("search condition=" + condition);
  final Map<String,Object> meta=new HashMap<>();
  StopWatch stopWatch=new StopWatch();
  stopWatch.start();
  String queryFormat="%s[%s]{*}";
  String queryString=String.format(queryFormat,Constants.MR_JOB_EXECUTION_SERVICE_NAME,condition);
  GenericServiceAPIResponseEntity<TaggedLogAPIEntity> res=ResourceUtils.getQueryResult(queryString,null,null);
  if (res.isSuccess() && res.getObj() != null) {
    for (    TaggedLogAPIEntity o : res.getObj()) {
      jobs.add(o);
      jobIds.add(o.getTags().get(MRJobTagName.JOB_ID.toString()));
    }
  }
  queryString=String.format(queryFormat,Constants.MR_RUNNING_JOB_EXECUTION_SERVICE_NAME,condition);
  res=ResourceUtils.getQueryResult(queryString,null,null);
  if (res.isSuccess() && res.getObj() != null) {
    for (    TaggedLogAPIEntity o : res.getObj()) {
      String key=o.getTags().get(MRJobTagName.JOB_ID.toString());
      if (!ResourceUtils.isDuplicate(jobIds,key)) {
        jobs.add(o);
      }
    }
  }
  if (jobs.size() > 0) {
    Collections.sort(jobs,new Comparator<TaggedLogAPIEntity>(){
      @Override public int compare(      TaggedLogAPIEntity o1,      TaggedLogAPIEntity o2){
        return o1.getTimestamp() > o2.getTimestamp() ? 1 : (o1.getTimestamp() == o2.getTimestamp() ? 0 : -1);
      }
    }
);
  }
  stopWatch.stop();
  if (res.isSuccess()) {
    response.setSuccess(true);
  }
 else {
    response.setSuccess(false);
    response.setException(new Exception(res.getException()));
  }
  meta.put(TOTAL_RESULTS,jobs.size());
  meta.put(ELAPSEDMS,stopWatch.getTime());
  response.setObj(jobs);
  response.setMeta(meta);
  return response;
}