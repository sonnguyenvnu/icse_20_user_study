public String decode(String shortUrl){
  String[] parts=shortUrl.split("http://tinyurl.com/");
  String code=parts[1];
  return list.get(Integer.parseInt(code) - 1);
}
