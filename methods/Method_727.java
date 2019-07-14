public String[] decode(String content) throws IOException {
  return JSON.parseObject(content,String[].class);
}
