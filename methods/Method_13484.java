@GetMapping("/file-resource") public String fileResource(){
  try {
    return "get file resource success. content: " + StreamUtils.copyToString(file.getInputStream(),Charset.forName(CharEncoding.UTF_8));
  }
 catch (  Exception e) {
    e.printStackTrace();
    return "get resource fail: " + e.getMessage();
  }
}
