@Override public boolean next(){
  while (true) {
    if (cursor == null) {
      nextCursor();
      if (cursor == null) {
        return false;
      }
    }
    if (cursor.next()) {
      return true;
    }
    cursor=null;
  }
}
