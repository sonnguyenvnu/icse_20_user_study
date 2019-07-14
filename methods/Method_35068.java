@Nonnull @Override public Observable<Parsed> stream(){
  return subject.hide().map(AbstractMap.SimpleEntry::getValue);
}
