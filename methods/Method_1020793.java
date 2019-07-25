@Override public Authentication authenticate(Authentication authentication) throws AuthenticationException {
  MobileAuthenticationToken mobileAuthenticationToken=(MobileAuthenticationToken)authentication;
  String mobile=mobileAuthenticationToken.getPrincipal().toString();
  String realCode=redisTemplate.opsForValue().get(SecurityConstants.REDIS_CODE_PREFIX + mobile);
  String inputCode=authentication.getCredentials().toString();
  if (realCode == null) {
    logger.debug("??????????????????????");
    throw new BadCredentialsException("???????????");
  }
  if (!inputCode.equalsIgnoreCase(realCode)) {
    logger.debug("???????????????");
    throw new BadCredentialsException("???????????");
  }
  SysUserVo sysUserVo=sysUserService.loadUserByMobile(mobile);
  if (sysUserVo == null) {
    logger.debug("??????????");
    throw new UsernameNotFoundException("????, ???????");
  }
  UserDetailsImpl userDetails=new UserDetailsImpl(sysUserVo);
  MobileAuthenticationToken authenticationToken=new MobileAuthenticationToken(userDetails,inputCode,userDetails.getAuthorities());
  authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
  return authenticationToken;
}
