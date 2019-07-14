public void addBook(String[] i){
synchronized (books) {
    books.add(i);
  }
}
