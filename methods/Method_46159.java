protected List<String> flatUserData(UserData userData){
  List<String> result=new ArrayList<String>();
  Map<String,List<String>> zoneData=userData.getZoneData();
  for (  Map.Entry<String,List<String>> entry : zoneData.entrySet()) {
    result.addAll(entry.getValue());
  }
  return result;
}
