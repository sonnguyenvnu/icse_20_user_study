private String splice(int index,String... alternatives){
  if (index < head || index > tail || head > tail) {
    return null;
  }
  String token=list.get(index);
  if (alternatives.length == 0) {
    return token;
  }
 else {
    for (    String alt : alternatives) {
      if (alt.equals(token)) {
        return token;
      }
    }
    return null;
  }
}
