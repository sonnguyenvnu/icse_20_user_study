protected final ResponseEntity<String> reply(Event event){
  sendTypingOffIndicator(event.getRecipient());
  logger.debug("Send message: {}",event.toString());
  try {
    return restTemplate.postForEntity(fbSendUrl,event,String.class);
  }
 catch (  HttpClientErrorException e) {
    logger.error("Send message error: Response body: {} \nException: ",e.getResponseBodyAsString(),e);
    return new ResponseEntity<>(e.getResponseBodyAsString(),e.getStatusCode());
  }
}
