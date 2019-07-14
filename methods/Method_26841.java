@GetMapping("/atom") public Feed writeFeed(){
  Feed feed=new Feed();
  feed.setFeedType("atom_1.0");
  feed.setTitle("My Atom feed");
  return feed;
}
