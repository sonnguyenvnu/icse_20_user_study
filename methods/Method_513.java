public final boolean isRef(){
  if (sp != 4) {
    return false;
  }
  return charAt(np + 1) == '$' && charAt(np + 2) == 'r' && charAt(np + 3) == 'e' && charAt(np + 4) == 'f';
}
