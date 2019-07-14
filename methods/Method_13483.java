@RequestMapping("/user") public String simple(){
  return "Hello Nacos Config!" + "Hello " + userName + " " + age + "!";
}
