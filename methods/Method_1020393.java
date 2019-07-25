/** 
 * Performs a binary search on a sorted list, returning the index of the given value if it is present or {@code (-(insertion point) - 1)} if the value is not present.If the list is not sorted, then reverts to linear search and returns {@code -size()} if the element is not found.
 * @param value value to find
 * @return index of value or {@code (-(insertion point) - 1)} if thevalue is not present
 */
public int binarysearch(int value){
  int sz=size;
  if (!sorted) {
    for (int i=0; i < sz; i++) {
      if (values[i] == value) {
        return i;
      }
    }
    return -sz;
  }
  int min=-1;
  int max=sz;
  while (max > (min + 1)) {
    int guessIdx=min + ((max - min) >> 1);
    int guess=values[guessIdx];
    if (value <= guess) {
      max=guessIdx;
    }
 else {
      min=guessIdx;
    }
  }
  if ((max != sz)) {
    return (value == values[max]) ? max : (-max - 1);
  }
 else {
    return -sz - 1;
  }
}
