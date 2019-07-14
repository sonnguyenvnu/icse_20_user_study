@Override public int getItemViewType(int i){
  if (i == 0) {
    return 0;
  }
 else   if (i == 1) {
    return 1;
  }
  i-=2;
  if (accountsShowed) {
    if (i < accountNumbers.size()) {
      return 4;
    }
 else {
      if (accountNumbers.size() < UserConfig.MAX_ACCOUNT_COUNT) {
        if (i == accountNumbers.size()) {
          return 5;
        }
 else         if (i == accountNumbers.size() + 1) {
          return 2;
        }
      }
 else {
        if (i == accountNumbers.size()) {
          return 2;
        }
      }
    }
    i-=getAccountRowsCount();
  }
  if (i == 3) {
    return 2;
  }
  return 3;
}
