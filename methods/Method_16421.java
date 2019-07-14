@GetMapping("/boot/{serverId}") @ApiOperation("???OAuth2.0??????") public RedirectView boot(@PathVariable String serverId,@RequestParam(defaultValue="/") String redirect,HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
  OAuth2ServerConfigEntity entity=oAuth2ServerConfigService.selectByPk(serverId);
  if (entity == null) {
    return new RedirectView("/401.html");
  }
  String callback=WebUtil.getBasePath(request).concat("oauth2/callback/").concat(serverId).concat("/?redirect=").concat(URLEncoder.encode(redirect,"UTF-8"));
  RedirectView view=new RedirectView(entity.getRealUrl(entity.getAuthUrl()));
  view.addStaticAttribute(OAuth2Constants.response_type,"code");
  view.addStaticAttribute(OAuth2Constants.state,requestState(session).getResult());
  view.addStaticAttribute(OAuth2Constants.client_id,entity.getClientId());
  view.addStaticAttribute(OAuth2Constants.redirect_uri,callback);
  return view;
}
