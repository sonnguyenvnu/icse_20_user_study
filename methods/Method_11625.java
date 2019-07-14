public static List<Request> convertToRequests(Collection<String> urls){
  List<Request> requestList=new ArrayList<Request>(urls.size());
  for (  String url : urls) {
    requestList.add(new Request(url));
  }
  return requestList;
}
