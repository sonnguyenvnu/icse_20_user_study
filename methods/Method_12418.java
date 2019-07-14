protected Object createDiscordNotification(InstanceEvent event,Instance instance){
  Map<String,Object> body=new HashMap<>();
  body.put("content",createContent(event,instance));
  body.put("tts",tts);
  if (avatarUrl != null) {
    body.put("avatar_url",avatarUrl);
  }
  if (username != null) {
    body.put("username",username);
  }
  HttpHeaders headers=new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_JSON);
  headers.add(HttpHeaders.USER_AGENT,"RestTemplate");
  return new HttpEntity<>(body,headers);
}
