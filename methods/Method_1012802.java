/** 
 * Flips the default alignment of the given column specification and returns a new column specification object with the flipped alignment and the same size and growing behavior as the original.
 * @param spec the original column specification
 * @return the column specification with flipped default alignment
 */
private static ColumnSpec flipped(ColumnSpec spec){
  DefaultAlignment alignment=spec.getDefaultAlignment();
  if (alignment == ColumnSpec.LEFT) {
    alignment=ColumnSpec.RIGHT;
  }
 else {
    if (alignment == ColumnSpec.RIGHT) {
      alignment=ColumnSpec.LEFT;
    }
  }
  return new ColumnSpec(alignment,spec.getSize(),spec.getResizeWeight());
}
