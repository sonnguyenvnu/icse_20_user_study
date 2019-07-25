private Try<Void> publish(PatronEvent.BookHoldExpired event){
  return Try.run(() -> patronRepository.publish(event));
}
