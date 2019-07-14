private int getCurrentIndex(){
  return (int)(currentIndex.get() % maxSize);
}
