private void populateResultArea(){
  int[] selectionIndices=resultsTable.getSelectedRows();
  TableModel model=resultsTable.getModel();
  List<Match> selections=new ArrayList<>(selectionIndices.length);
  for (int i=0; i < selectionIndices.length; i++) {
    selections.add((Match)model.getValueAt(selectionIndices[i],99));
  }
  String report=new SimpleRenderer(trimLeadingWhitespace).render(selections.iterator());
  resultsTextArea.setText(report);
  resultsTextArea.setCaretPosition(0);
}
