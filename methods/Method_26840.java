@PostMapping("/atom") public String readFeed(@RequestBody Feed feed){
  return "Read " + feed.getTitle();
}
