public String decode(String shortUrl){
  return map.get(shortUrl.replace("http://tinyurl.com/",""));
}
