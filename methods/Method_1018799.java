void create(){
  cellSize=Utils.dpToPx(getContext(),CELL_SIZE_DP);
  borderSize=Utils.dpToPx(getContext(),BORDER_SIZE_DP);
  stepSize=cellSize + borderSize;
  empty.setColor(ContextCompat.getColor(getContext(),R.color.pieces_cell));
  complete.setColor(ContextCompat.getColor(getContext(),R.color.accent));
}
