@RequestMapping("/photos/user/message") @ResponseBody public String getTrustedUserMessage(Principal principal){
  return "Hello, Trusted User" + (principal != null ? " " + principal.getName() : "");
}
