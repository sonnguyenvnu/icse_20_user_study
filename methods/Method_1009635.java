@PostConstruct public void init(){
  table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
    public void valueChanged(    ListSelectionEvent e){
      if (e.getValueIsAdjusting())       return;
      int index=((ListSelectionModel)e.getSource()).getMinSelectionIndex();
      if (index > -1) {
        presenter.onPortMappingSelected(table.getPortMapping(index));
      }
    }
  }
);
}
