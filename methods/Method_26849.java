@GetMapping("/viewName") public void usingRequestToViewNameTranslator(Model model){
  model.addAttribute("foo","bar");
  model.addAttribute("fruit","apple");
}
