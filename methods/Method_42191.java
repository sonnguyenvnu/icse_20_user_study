public boolean clearSelected(){
  Set<Integer> oldSelections=new HashSet<>(selectedPositions);
  selectedPositions.clear();
  for (  int selectedPos : oldSelections)   notifyItemChanged(selectedPos);
  return true;
}
