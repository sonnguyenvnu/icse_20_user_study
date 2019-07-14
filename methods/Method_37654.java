/** 
 * Detects the longest character reference name on given position in char array.
 */
public static String detectName(final char[] input,int ndx){
  final Ptr ptr=new Ptr();
  int firstIndex=0;
  int lastIndex=ENTITY_NAMES.length - 1;
  int len=input.length;
  char[] lastName=null;
  final BinarySearchBase binarySearch=new BinarySearchBase(){
    @Override protected int compare(    final int index){
      char[] name=ENTITY_NAMES[index];
      if (ptr.offset >= name.length) {
        return -1;
      }
      return name[ptr.offset] - ptr.c;
    }
  }
;
  while (true) {
    ptr.c=input[ndx];
    if (!CharUtil.isAlphaOrDigit(ptr.c)) {
      return lastName != null ? new String(lastName) : null;
    }
    firstIndex=binarySearch.findFirst(firstIndex,lastIndex);
    if (firstIndex < 0) {
      return lastName != null ? new String(lastName) : null;
    }
    char[] element=ENTITY_NAMES[firstIndex];
    if (element.length == ptr.offset + 1) {
      lastName=ENTITY_NAMES[firstIndex];
    }
    lastIndex=binarySearch.findLast(firstIndex,lastIndex);
    if (firstIndex == lastIndex) {
      for (int i=ptr.offset; i < element.length; i++) {
        if (element[i] != input[ndx]) {
          return lastName != null ? new String(lastName) : null;
        }
        ndx++;
      }
      return new String(element);
    }
    ptr.offset++;
    ndx++;
    if (ndx == len) {
      return lastName != null ? new String(lastName) : null;
    }
  }
}
