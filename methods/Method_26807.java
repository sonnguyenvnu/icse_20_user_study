@GetMapping("cookie") public String withCookie(@CookieValue String openid_provider){
  return "Obtained 'openid_provider' cookie '" + openid_provider + "'";
}
