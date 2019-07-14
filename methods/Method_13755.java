@Override @Scheduled(cron="${backup.cron}") public void sendBackupNotifications(){
  final NotificationType type=NotificationType.BACKUP;
  List<Recipient> recipients=recipientService.findReadyToNotify(type);
  log.info("found {} recipients for backup notification",recipients.size());
  recipients.forEach(recipient -> CompletableFuture.runAsync(() -> {
    try {
      String attachment=client.getAccount(recipient.getAccountName());
      emailService.send(type,recipient,attachment);
      recipientService.markNotified(type,recipient);
    }
 catch (    Throwable t) {
      log.error("an error during backup notification for {}",recipient,t);
    }
  }
));
}
