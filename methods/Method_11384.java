/** 
 * Tell the column manager the new target text that it should display.
 */
void setText(char[] text){
  if (characterList == null) {
    throw new IllegalStateException("Need to call setCharacterList(char[]) first.");
  }
  for (int i=0; i < mRxTickerColumns.size(); ) {
    final RxTickerColumn rxTickerColumn=mRxTickerColumns.get(i);
    if (rxTickerColumn.getCurrentWidth() > 0) {
      i++;
    }
 else {
      mRxTickerColumns.remove(i);
    }
  }
  final int[] actions=RxLevenshteinUtils.computeColumnActions(getCurrentText(),text);
  int columnIndex=0;
  int textIndex=0;
  for (int i=0; i < actions.length; i++) {
switch (actions[i]) {
case RxLevenshteinUtils.ACTION_INSERT:
      mRxTickerColumns.add(columnIndex,new RxTickerColumn(characterList,characterIndicesMap,metrics));
case RxLevenshteinUtils.ACTION_SAME:
    mRxTickerColumns.get(columnIndex).setTargetChar(text[textIndex]);
  columnIndex++;
textIndex++;
break;
case RxLevenshteinUtils.ACTION_DELETE:
mRxTickerColumns.get(columnIndex).setTargetChar(RxTickerUtils.EMPTY_CHAR);
columnIndex++;
break;
default :
throw new IllegalArgumentException("Unknown action: " + actions[i]);
}
}
}
