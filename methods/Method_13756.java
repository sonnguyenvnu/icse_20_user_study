@Override @Scheduled(cron="${remind.cron}") public void sendRemindNotifications(){
  final NotificationType type=NotificationType.REMIND;
  List<Recipient> recipients=recipientService.findReadyToNotify(type);
  log.info("found {} recipients for remind notification",recipients.size());
  recipients.forEach(recipient -> CompletableFuture.runAsync(() -> {
    try {
      emailService.send(type,recipient,null);
      recipientService.markNotified(type,recipient);
    }
 catch (    Throwable t) {
      log.error("an error during remind notification for {}",recipient,t);
    }
  }
));
}
