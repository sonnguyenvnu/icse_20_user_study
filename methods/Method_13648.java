@Override public List<String> getServices(){
  Set<String> publishers=NamingService.getPublishes();
  Set<String> doms=NamingService.getDomsSubscribed();
  doms.addAll(publishers);
  List<String> result=new LinkedList<>();
  for (  String service : doms) {
    result.add(service);
  }
  return result;
}
