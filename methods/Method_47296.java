public void addBook(final String[] i,boolean refreshdrawer){
synchronized (books) {
    books.add(i);
  }
  if (refreshdrawer && dataChangeListener != null) {
    dataChangeListener.onBookAdded(i,true);
  }
}
