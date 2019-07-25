private int smallest(){
  int result=-1;
  for (  Integer i : q) {
    if (result == -1 || dist[i] < dist[result]) {
      result=i;
    }
  }
  return result;
}
