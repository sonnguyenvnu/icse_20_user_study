public Iterator<MessageMemTable> iterator(){
  return currentActive.descendingIterator();
}
