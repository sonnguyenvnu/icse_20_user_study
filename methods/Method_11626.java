public static List<String> convertToUrls(Collection<Request> requests){
  List<String> urlList=new ArrayList<String>(requests.size());
  for (  Request request : requests) {
    urlList.add(request.getUrl());
  }
  return urlList;
}
