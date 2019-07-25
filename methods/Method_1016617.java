@Override public <E extends Exception>void info(LoggerConsumer<E> consumer) throws E {
  consumer.accept(this);
}
