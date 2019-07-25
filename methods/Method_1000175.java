@GetMapping("/thymeleaf") public String hello(HttpServletRequest request,@RequestParam(value="description",required=false,defaultValue="springboot-thymeleaf") String description){
  request.setAttribute("description",description);
  return "thymeleaf";
}
