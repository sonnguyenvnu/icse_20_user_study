@Override public boolean match(StringLocated s){
  invoked=true;
  final long foxRegex=foxRegex();
  if (foxRegex != 0L) {
    final long foxLine=s.getFoxSignature();
    final long check=foxRegex & foxLine;
    if (check != foxRegex) {
      return false;
    }
  }
  return super.match(s);
}
