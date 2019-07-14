@MessageMapping("/chat") public void handleChat(Principal principal,Message message){
  if (principal.getName().equals("admin")) {
    messagingTemplate.convertAndSendToUser("abel","/queue/notifications",principal.getName() + "-send:" + message.getName());
  }
 else {
    messagingTemplate.convertAndSendToUser("admin","/queue/notifications",principal.getName() + "-send:" + message.getName());
  }
}
