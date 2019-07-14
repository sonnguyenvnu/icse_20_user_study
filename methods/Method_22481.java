private void createComponents(){
  categoryLabel=new JLabel(Language.text("contrib.category"));
  categoryChooser=new JComboBox<String>();
  categoryChooser.setMaximumRowCount(20);
  categoryChooser.setFont(ManagerFrame.NORMAL_PLAIN);
  updateCategoryChooser();
  categoryChooser.addItemListener(new ItemListener(){
    public void itemStateChanged(    ItemEvent e){
      category=(String)categoryChooser.getSelectedItem();
      if (ManagerFrame.ANY_CATEGORY.equals(category)) {
        category=null;
      }
      filterLibraries(category,filterField.filters);
      contributionListPanel.updateColors();
    }
  }
);
  filterField=new FilterField();
}
