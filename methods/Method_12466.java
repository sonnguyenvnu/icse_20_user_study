@Override public Mono<Void> doNotify(InstanceEvent event,Instance instance){
  return this.delegate.notify(event).doFinally(s -> {
    if (shouldEndReminder(event)) {
      this.reminders.remove(event.getInstance());
    }
 else     if (shouldStartReminder(event)) {
      this.reminders.putIfAbsent(event.getInstance(),new Reminder(event));
    }
  }
).onErrorResume(e -> Mono.empty());
}
