@Override public void configure(final TimeBefore annotation){
  time=LocalDateTime.parse(annotation.value());
}
