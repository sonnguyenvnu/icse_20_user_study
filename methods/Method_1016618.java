@Override public <E extends Exception>void warn(LoggerConsumer<E> consumer) throws E {
  consumer.accept(this);
}
