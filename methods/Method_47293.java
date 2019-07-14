public void removeBook(int i){
synchronized (books) {
    if (books.size() > i)     books.remove(i);
  }
}
