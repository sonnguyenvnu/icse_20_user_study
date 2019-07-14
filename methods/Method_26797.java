@GetMapping("primitive") public String primitive(@RequestParam Integer value){
  return "Converted primitive " + value;
}
