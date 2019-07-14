@PostMapping("/form") public String readForm(@ModelAttribute JavaBean bean){
  return "Read x-www-form-urlencoded: " + bean;
}
