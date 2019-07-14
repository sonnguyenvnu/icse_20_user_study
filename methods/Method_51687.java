private void init(){
  model.addViewerModelListener(this);
  list=new JList();
  list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  list.addListSelectionListener(new ListSelectionListener(){
    @Override public void valueChanged(    ListSelectionEvent e){
      if (list.getSelectedValue() != null) {
        model.selectNode((Node)list.getSelectedValue(),EvaluationResultsPanel.this);
      }
    }
  }
);
  add(new JScrollPane(list),BorderLayout.CENTER);
}
