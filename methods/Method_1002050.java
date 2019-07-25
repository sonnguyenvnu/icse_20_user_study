/** 
 * Backup all secrets for a given group. Returns an encrypted encrypted to the backup key in the main configuration file. Only accessible to automation clients.
 * @excludeParams automationClient
 * @param name Group name
 * @return Encrypted archive
 */
@Timed @ExceptionMetered @GET @Path("{key}/group/{group}") @Produces(APPLICATION_OCTET_STREAM) public byte[] backup(@Auth AutomationClient automationClient,@PathParam("group") String name,@PathParam("key") String key){
  if (config.getBackupExportKey(key) == null) {
    throw new NotFoundException("Unknown key: " + key);
  }
  Optional<Group> groupOptional=groupDAO.getGroup(name);
  if (!groupOptional.isPresent()) {
    throw new NotFoundException("Unknown group: " + name);
  }
  Group group=groupOptional.get();
  List<SecretDeliveryResponse> secrets=secretController.getSecretsForGroup(group).stream().map(SecretDeliveryResponse::fromSecret).collect(toList());
  String serialized;
  try {
    serialized=objectMapper.writeValueAsString(secrets);
  }
 catch (  JsonProcessingException e) {
    logger.error("Unable to backup secrets",e);
    throw new InternalServerErrorException("Unable to backup secrets, check logs for details");
  }
  Map<String,String> auditInfo=secrets.stream().collect(toMap(SecretDeliveryResponse::getName,SecretDeliveryResponse::getChecksum));
  auditLog.recordEvent(new Event(now(),GROUP_BACKUP,automationClient.getName(),group.getName(),auditInfo));
  try {
    Key exportKey=new Key(config.getBackupExportKey(key));
    Encryptor encryptor=new Encryptor(exportKey);
    encryptor.setEncryptionAlgorithm(AES256);
    encryptor.setSigningAlgorithm(Unsigned);
    encryptor.setCompressionAlgorithm(ZIP);
    ByteArrayInputStream plaintext=new ByteArrayInputStream(serialized.getBytes(UTF_8));
    ByteArrayOutputStream ciphertext=new ByteArrayOutputStream();
    encryptor.encrypt(plaintext,ciphertext,new FileMetadata(format("%s.json",group),UTF8));
    return ciphertext.toByteArray();
  }
 catch (  PGPException|IOException e) {
    logger.error("Unable to backup secrets",e);
    throw new InternalServerErrorException("Unable to backup secrets, check logs for details");
  }
}
