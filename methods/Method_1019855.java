private void fill(EventFactory<E> eventFactory){
  for (int i=0; i < bufferSize; i++) {
    entries[BUFFER_PAD + i]=eventFactory.newInstance();
  }
}
