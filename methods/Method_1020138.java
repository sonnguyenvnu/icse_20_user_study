@GetMapping("auth") public String index(){
  if (null != UserContext.getCurrent() && null != UserContext.getCurrent().getUser()) {
    return "/layout/main";
  }
  return "/security/login";
}
