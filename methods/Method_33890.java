@RequestMapping("/oauth/clients/{client}/tokens") @ResponseBody public Collection<OAuth2AccessToken> listTokensForClient(@PathVariable String client) throws Exception {
  return enhance(tokenStore.findTokensByClientId(client));
}
