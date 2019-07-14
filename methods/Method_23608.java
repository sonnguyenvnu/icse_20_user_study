public void swap(int a,int b){
  String tkey=keys[a];
  float tvalue=values[a];
  keys[a]=keys[b];
  values[a]=values[b];
  keys[b]=tkey;
  values[b]=tvalue;
}
