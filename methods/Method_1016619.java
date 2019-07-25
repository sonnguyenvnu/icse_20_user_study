@Override public <E extends Exception>void error(LoggerConsumer<E> consumer) throws E {
  consumer.accept(this);
}
