@PostMapping("/rss") public String readChannel(@RequestBody Channel channel){
  return "Read " + channel.getTitle();
}
