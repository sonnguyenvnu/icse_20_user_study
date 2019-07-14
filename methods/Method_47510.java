@RequestMapping("/Welcome1") @ResponseBody public String say2() throws Exception {
  ws.sendMessage();
  return "is ok";
}
