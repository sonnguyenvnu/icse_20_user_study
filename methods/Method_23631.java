public int get(String key,int alternate){
  int index=index(key);
  if (index == -1)   return alternate;
  return values[index];
}
