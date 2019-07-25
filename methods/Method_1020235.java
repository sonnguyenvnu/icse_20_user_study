@PostMapping("postform") public String postform(User u){
  System.out.println("???" + u.getName());
  System.out.println("???" + u.getAge());
  return "redirect:/th/test";
}
