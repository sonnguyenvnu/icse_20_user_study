@PostMapping("postform") public String postform(User user){
  System.out.println("???" + user.getName());
  System.out.println("???" + user.getAge());
  return "redirect:/th/test";
}
