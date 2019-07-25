@Override public int size(){
  return (int)getRocksDBDAO().prefixSearch(columnFamilyName,"").count();
}
