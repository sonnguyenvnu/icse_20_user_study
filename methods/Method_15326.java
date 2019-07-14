/** 
 * ??
 * @param request ??String???encode??decode
 * @param session
 * @return
 * @see {@link RequestMethod#PUT}
 */
@PostMapping("put") public String put(@RequestBody String request,HttpSession session){
  return new DemoParser(PUT).setSession(session).parse(request);
}
