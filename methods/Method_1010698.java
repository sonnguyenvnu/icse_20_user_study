/** 
 * Compares two cells. Cell which is first is the editor is lesser. <p/> Comparing cells must have common parent. Check getCommonParent(firstCell, secondCell) != null
 * @param firstCell  a first cell to be compared.
 * @param secondCell a second cell to be compared.
 * @return -1, zero, or 1 as the first cellis less than, equal to, or greater than the second cell.
 * @throws java.lang.IllegalArgumentException if the first cell andthe second cell don't have common parent.
 */
public static int compare(@NotNull EditorCell firstCell,@NotNull EditorCell secondCell){
  if (firstCell.equals(secondCell)) {
    return 0;
  }
  List<EditorCell> firstCellAndParents=new ArrayList<>();
  EditorCell parent=firstCell;
  while (parent != null) {
    if (parent.equals(secondCell)) {
      return 1;
    }
    firstCellAndParents.add(parent);
    parent=parent.getParent();
  }
  EditorCell_Collection commonParent=secondCell.getParent();
  EditorCell secondChild=secondCell;
  while (commonParent != null && !firstCellAndParents.contains(commonParent)) {
    secondChild=commonParent;
    commonParent=commonParent.getParent();
  }
  if (commonParent == null) {
    throw new IllegalArgumentException(firstCell.toString() + " and " + secondCell.toString() + " don't have common parent");
  }
  if (commonParent.equals(firstCell)) {
    return -1;
  }
  EditorCell firstChild=firstCellAndParents.get(firstCellAndParents.indexOf(commonParent) - 1);
  if (findInNextSiblings(firstChild,secondChild)) {
    return -1;
  }
  if (findInNextSiblings(secondChild,firstChild)) {
    return 1;
  }
  for (  EditorCell cell : commonParent) {
    if (cell == firstChild) {
      return -1;
    }
    if (cell == secondChild) {
      return 1;
    }
  }
  return 0;
}
