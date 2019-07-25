@Override public <E extends Exception>void trace(LoggerConsumer<E> consumer) throws E {
  consumer.accept(this);
}
