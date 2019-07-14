public void swap(int a,int b){
  String tkey=keys[a];
  String tvalue=values[a];
  keys[a]=keys[b];
  values[a]=values[b];
  keys[b]=tkey;
  values[b]=tvalue;
}
