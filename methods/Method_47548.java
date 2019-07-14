/** 
 * Adds swatches to table in a serpentine format.
 */
public void drawPalette(int[] colors,int selectedColor){
  if (colors == null) {
    return;
  }
  this.removeAllViews();
  int tableElements=0;
  int rowElements=0;
  int rowNumber=0;
  TableRow row=createTableRow();
  for (  int color : colors) {
    tableElements++;
    View colorSwatch=createColorSwatch(color,selectedColor);
    setSwatchDescription(rowNumber,tableElements,rowElements,color == selectedColor,colorSwatch);
    addSwatchToRow(row,colorSwatch,rowNumber);
    rowElements++;
    if (rowElements == mNumColumns) {
      addView(row);
      row=createTableRow();
      rowElements=0;
      rowNumber++;
    }
  }
  if (rowElements > 0) {
    while (rowElements != mNumColumns) {
      addSwatchToRow(row,createBlankSpace(),rowNumber);
      rowElements++;
    }
    addView(row);
  }
}
