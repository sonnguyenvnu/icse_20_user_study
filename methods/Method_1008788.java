@Override public String login(String username,String password){
  UsernamePasswordAuthenticationToken upToken=new UsernamePasswordAuthenticationToken(username,password);
  final Authentication authentication=authenticationManager.authenticate(upToken);
  SecurityContextHolder.getContext().setAuthentication(authentication);
  final UserDetails userDetails=userDetailsService.loadUserByUsername(username);
  final String token=jwtTokenUtil.generateToken(userDetails);
  return token;
}
