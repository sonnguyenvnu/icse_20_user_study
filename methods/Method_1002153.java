/** 
 * ????
 * @param authorizationUser
 * @return
 */
@Log("????") @PostMapping(value="${jwt.auth.path}") public ResponseEntity login(@Validated @RequestBody AuthorizationUser authorizationUser){
  String code=redisService.getCodeVal(authorizationUser.getUuid());
  redisService.delete(authorizationUser.getUuid());
  if (StringUtils.isBlank(code)) {
    throw new BadRequestException("??????");
  }
  if (StringUtils.isBlank(authorizationUser.getCode()) || !authorizationUser.getCode().equalsIgnoreCase(code)) {
    throw new BadRequestException("?????");
  }
  final JwtUser jwtUser=(JwtUser)userDetailsService.loadUserByUsername(authorizationUser.getUsername());
  if (!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))) {
    throw new AccountExpiredException("????");
  }
  if (!jwtUser.isEnabled()) {
    throw new AccountExpiredException("????????????");
  }
  final String token=jwtTokenUtil.generateToken(jwtUser);
  return ResponseEntity.ok(new AuthenticationInfo(token,jwtUser));
}
