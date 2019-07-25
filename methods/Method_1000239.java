public String encode(String longUrl){
  list.add(longUrl);
  return URL.concat(String.valueOf(list.size()));
}
