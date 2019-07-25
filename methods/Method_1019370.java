String format(){
  return apiKey + ":" + Base64.getEncoder().encodeToString(hmac.getBytes());
}
