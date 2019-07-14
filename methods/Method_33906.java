@RequestMapping("/facebook/info") public String photos(Model model) throws Exception {
  ObjectNode result=facebookRestTemplate.getForObject("https://graph.facebook.com/me/friends",ObjectNode.class);
  ArrayNode data=(ArrayNode)result.get("data");
  ArrayList<String> friends=new ArrayList<String>();
  for (  JsonNode dataNode : data) {
    friends.add(dataNode.get("name").asText());
  }
  model.addAttribute("friends",friends);
  return "facebook";
}
