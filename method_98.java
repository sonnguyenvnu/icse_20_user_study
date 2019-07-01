@Override public void _XXXXX_(String invalidContentConsumer,AuditInformation auditInformation) throws RepositoryAdminException {
  Configuration configuration=getArchivaConfiguration().getConfiguration();
  configuration.getRepositoryScanning().removeInvalidContentConsumer(invalidContentConsumer);
  saveConfiguration(configuration);
  triggerAuditEvent("","",AuditEvent.DISABLE_REPO_CONSUMER,auditInformation);
}