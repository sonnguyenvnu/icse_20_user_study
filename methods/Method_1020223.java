@RequestMapping("login") private @ResponseBody String hello(@RequestParam(value="username",required=false) String username,@RequestParam(value="password",required=false) String password){
  return "Hello " + username + ",Your password is: " + password;
}
