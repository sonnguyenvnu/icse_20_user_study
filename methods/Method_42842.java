public static String[] adaptToSetOfIDs(String resp){
  return resp.replaceAll("[\\[\\\"\\] ]","").split(",");
}
