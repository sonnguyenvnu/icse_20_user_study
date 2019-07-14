protected Mono<Void> sendReminders(){
  Instant now=Instant.now();
  return Flux.fromIterable(this.reminders.values()).filter(reminder -> reminder.getLastNotification().plus(this.reminderPeriod).isBefore(now)).flatMap(reminder -> this.delegate.notify(reminder.getEvent()).doOnSuccess(signal -> reminder.setLastNotification(now))).then();
}
