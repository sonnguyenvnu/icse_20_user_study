@GetMapping("/rss") public Channel writeChannel(){
  Channel channel=new Channel();
  channel.setFeedType("rss_2.0");
  channel.setTitle("My RSS feed");
  channel.setDescription("Description");
  channel.setLink("http://localhost:8080/mvc-showcase/rss");
  return channel;
}
