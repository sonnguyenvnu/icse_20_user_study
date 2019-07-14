@GetMapping("/validate") public String validate(@Valid JavaBean bean,BindingResult result){
  if (result.hasErrors()) {
    return "Object has validation errors";
  }
 else {
    return "No errors";
  }
}
