/** 
 * Redirects to the actual  {@code index.html}.
 * @return
 */
@GetMapping(value=EXPLORER) public View explorer(HttpServletRequest request){
  return getRedirectView(request,request.getRequestURI().endsWith(EXPLORER));
}
