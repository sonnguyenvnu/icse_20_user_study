private OAuth2Authentication extractAuthentication(Map<String,Object> map){
  Object principal=getPrincipal(map);
  OAuth2Request request=getRequest(map);
  List<GrantedAuthority> authorities=this.authoritiesExtractor.extractAuthorities(map);
  UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(principal,"N/A",authorities);
  token.setDetails(map);
  return new OAuth2Authentication(request,token);
}
