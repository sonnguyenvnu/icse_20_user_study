@GetMapping("path/{var}") public String withPathVariable(@PathVariable String var){
  return "Obtained 'var' path variable value '" + var + "'";
}
