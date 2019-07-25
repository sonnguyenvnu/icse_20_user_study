public static Money total(Collection<Entry> entries){
  if (entries.isEmpty()) {
    return null;
  }
  Money result=null;
  for (  Entry e : entries) {
    if (null == result) {
      result=e.getAmount();
    }
 else     result=result.add(e.getAmount());
  }
  return result;
}
