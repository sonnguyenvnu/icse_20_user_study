private static boolean intable(int[][] table,int c){
  if (c < table[0][0])   return false;
  int bot=0;
  int top=table.length - 1;
  while (top >= bot) {
    int mid=(bot + top) / 2;
    if (table[mid][1] < c) {
      bot=mid + 1;
    }
 else     if (table[mid][0] > c) {
      top=mid - 1;
    }
 else {
      return true;
    }
  }
  return false;
}
