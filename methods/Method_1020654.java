@RequestMapping("/relay") public String relay(){
  SecurityContextHolder.getContext().getAuthentication();
  return "success";
}
