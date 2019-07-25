@Override public <E extends Exception>void debug(LoggerConsumer<E> consumer) throws E {
  consumer.accept(this);
}
