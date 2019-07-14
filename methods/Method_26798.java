@GetMapping("collection") public String collection(@RequestParam Collection<Integer> values){
  return "Converted collection " + values;
}
