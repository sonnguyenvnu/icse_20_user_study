private JobEventRdbSearch.Condition buildCondition(final UriInfo info,final String[] params) throws ParseException {
  int perPage=10;
  int page=1;
  if (!Strings.isNullOrEmpty(info.getQueryParameters().getFirst("per_page"))) {
    perPage=Integer.parseInt(info.getQueryParameters().getFirst("per_page"));
  }
  if (!Strings.isNullOrEmpty(info.getQueryParameters().getFirst("page"))) {
    page=Integer.parseInt(info.getQueryParameters().getFirst("page"));
  }
  String sort=info.getQueryParameters().getFirst("sort");
  String order=info.getQueryParameters().getFirst("order");
  Date startTime=null;
  Date endTime=null;
  Map<String,Object> fields=getQueryParameters(info,params);
  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  if (!Strings.isNullOrEmpty(info.getQueryParameters().getFirst("startTime"))) {
    startTime=simpleDateFormat.parse(info.getQueryParameters().getFirst("startTime"));
  }
  if (!Strings.isNullOrEmpty(info.getQueryParameters().getFirst("endTime"))) {
    endTime=simpleDateFormat.parse(info.getQueryParameters().getFirst("endTime"));
  }
  return new JobEventRdbSearch.Condition(perPage,page,sort,order,startTime,endTime,fields);
}
