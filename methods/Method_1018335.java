/** 
 * Redirects to the actual  {@code index.html}.
 * @return
 */
@GetMapping(path=BROWSER) View browser(HttpServletRequest request){
  return getRedirectView(request,request.getRequestURI().endsWith(BROWSER));
}
