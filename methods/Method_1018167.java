public void cleanup(){
  removeTemporaryProcessGroup();
  log.info("Success cleanup: Successfully cleaned up '" + importTemplate.getTemplateName() + "' in NiFi ");
}
