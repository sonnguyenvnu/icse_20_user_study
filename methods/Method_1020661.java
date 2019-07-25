public static PassportAuthentication convert(PassportUserDetails details){
  Collection<? extends GrantedAuthority> authoritys=details.getAuthorities();
  PassportAuthentication passportAuthentication=new PassportAuthentication(authoritys);
  passportAuthentication.setUserId(details.getUserId());
  passportAuthentication.setPrincipal(details.getUsername());
  return passportAuthentication;
}
