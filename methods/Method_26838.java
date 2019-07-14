@PostMapping("/json") public String readJson(@Valid @RequestBody JavaBean bean){
  return "Read from JSON: " + bean;
}
