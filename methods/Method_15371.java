/** 
 * ??
 * @param request ??String???encode??decode
 * @param session
 * @return
 * @see {@link RequestMethod#PUT}
 */
@PostMapping("put") public String put(@RequestBody String request,HttpSession session){
  return new DemoParser(PUT,true).setSession(session).parse(request);
}
