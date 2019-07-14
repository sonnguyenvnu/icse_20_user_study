private JComponent makeMatchList(){
  resultsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
    @Override public void valueChanged(    ListSelectionEvent e){
      populateResultArea();
    }
  }
);
  resultsTable.registerKeyboardAction(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      copyMatchListSelectionsToClipboard();
    }
  }
,"Copy",COPY_KEY_STROKE,JComponent.WHEN_FOCUSED);
  resultsTable.registerKeyboardAction(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      deleteMatchlistSelections();
    }
  }
,"Del",DELETE_KEY_STROKE,JComponent.WHEN_FOCUSED);
  int[] alignments=new int[matchColumns.length];
  for (int i=0; i < alignments.length; i++) {
    alignments[i]=matchColumns[i].alignment();
  }
  resultsTable.setDefaultRenderer(Object.class,new AlignmentRenderer(alignments));
  final JTableHeader header=resultsTable.getTableHeader();
  header.addMouseListener(new MouseAdapter(){
    @Override public void mouseClicked(    MouseEvent e){
      sortOnColumn(header.columnAtPoint(new Point(e.getX(),e.getY())));
    }
  }
);
  return new JScrollPane(resultsTable);
}
