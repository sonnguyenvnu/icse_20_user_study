@RequestMapping("/") String index(){
  return testConfigBean.getName() + "?" + testConfigBean.getAge();
}
