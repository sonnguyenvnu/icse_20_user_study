/** 
 * @param position cell's adapter position
 * @return the card index of given cell's position
 */
public int findCardIdxFor(int position){
  int low=0, mid=mCards.size(), high=mid - 1;
  while (low <= high) {
    mid=(low + high) >>> 1;
    Pair<Range<Integer>,L> pair=mCards.get(mid);
    if (pair == null)     return -1;
    if (pair.first.getLower() <= position && pair.first.getUpper() > position) {
      return mid;
    }
 else     if (pair.first.getUpper() <= position) {
      low=mid + 1;
    }
 else {
      high=mid - 1;
    }
  }
  return -1;
}
