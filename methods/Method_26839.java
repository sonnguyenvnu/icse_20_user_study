@GetMapping("/json") public JavaBean writeJson(){
  return new JavaBean("bar","apple");
}
