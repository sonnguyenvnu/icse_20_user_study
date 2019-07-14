@RequestMapping("/oauth/clients/{client}/users/{user}/tokens") @ResponseBody public Collection<OAuth2AccessToken> listTokensForUser(@PathVariable String client,@PathVariable String user,Principal principal) throws Exception {
  checkResourceOwner(user,principal);
  return enhance(tokenStore.findTokensByClientIdAndUserName(client,user));
}
