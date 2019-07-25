private boolean match(int i,int gb){
  if (gb < table[i]) {
    return false;
  }
  int j=i + 1;
  while (j < 26 && (table[j] == table[i])) {
    ++j;
  }
  if (j == 26) {
    return gb <= table[j];
  }
 else {
    return gb < table[j];
  }
}
