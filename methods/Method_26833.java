@PostMapping("/string") public String readString(@RequestBody String string){
  return "Read string '" + string + "'";
}
