/** 
 * Redirects requests to the API root asking for HTML to the HAL explorer.
 * @return
 */
@GetMapping(value={"/",""},produces=MediaType.TEXT_HTML_VALUE) View index(HttpServletRequest request){
  return getRedirectView(request,false);
}
