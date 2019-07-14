@GetMapping("index") public Object index(@AuthenticationPrincipal Authentication authentication,HttpServletRequest request){
  String header=request.getHeader("Authorization");
  String token=StringUtils.substringAfter(header,"bearer ");
  return Jwts.parser().setSigningKey("test_key".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
}
