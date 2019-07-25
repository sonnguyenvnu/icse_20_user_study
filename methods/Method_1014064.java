@Activate protected void activate(Map<String,Object> properties){
  Object serviceRanking=properties.get(Constants.SERVICE_RANKING);
  if (serviceRanking instanceof Integer) {
    rank=(Integer)serviceRanking;
  }
 else {
    rank=0;
  }
}
