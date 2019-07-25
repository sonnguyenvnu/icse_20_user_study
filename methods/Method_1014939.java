private void schedule(Callable<?> callable){
  if (random.nextDouble() < probability) {
    deque.add(callable);
    executorService.schedule(new DequeCallable(),delay,delayUnit);
  }
}
