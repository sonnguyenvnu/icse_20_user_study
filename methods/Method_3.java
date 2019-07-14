public String encode(String longUrl){
  String key=getKey();
  map.put(key,longUrl);
  count++;
  return "http://tinyurl.com/" + key;
}
