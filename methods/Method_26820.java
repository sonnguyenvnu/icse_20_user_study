@GetMapping("/path/*") public String byPathPattern(HttpServletRequest request){
  return "Mapped by path pattern ('" + request.getRequestURI() + "')";
}
