@RequestMapping(value="/authentication/register",method=RequestMethod.POST) public User register(@RequestBody User addedUser) throws AuthenticationException {
  return authService.register(addedUser);
}
