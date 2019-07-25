@Override public final boolean contains(CharSequence name,String value){
  requireNonNull(name,"name");
  requireNonNull(value,"value");
  final int h=AsciiString.hashCode(name);
  final int i=index(h);
  HeaderEntry e=entries[i];
  while (e != null) {
    if (e.hash == h && keyEquals(e.key,name) && AsciiString.contentEquals(e.value,value)) {
      return true;
    }
    e=e.next;
  }
  return false;
}
