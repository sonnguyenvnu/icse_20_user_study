@RequestMapping(path="/current",method=RequestMethod.PUT) public Object saveCurrentNotificationsSettings(Principal principal,@Valid @RequestBody Recipient recipient){
  return recipientService.save(principal.getName(),recipient);
}
