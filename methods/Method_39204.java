public <T>WebApp registerComponent(final Class<T> madvocComponent,final Consumer<T> componentConsumer){
  Objects.requireNonNull(madvocComponent);
  madvocComponents.add(ClassConsumer.of(madvocComponent,componentConsumer));
  return this;
}
