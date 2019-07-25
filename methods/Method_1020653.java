@GetMapping("/user") public Authentication user(Authentication authentication){
  return authentication;
}
