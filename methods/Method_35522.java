public static LimitAndSinceDatePaginator fromRequest(List<ServeEvent> source,Request request){
  return new LimitAndSinceDatePaginator(source,toInt(request.queryParameter("limit")),toDate(request.queryParameter("since")));
}
