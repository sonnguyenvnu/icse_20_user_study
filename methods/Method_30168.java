private List<SimpleCelebrity> transformResponse(CelebrityList response){
  List<SimpleCelebrity> celebrityList=new ArrayList<>();
  celebrityList.addAll(response.directors);
  for (  SimpleCelebrity celebrity : celebrityList) {
    celebrity.isDirector=true;
  }
  celebrityList.addAll(response.actors);
  return celebrityList;
}
