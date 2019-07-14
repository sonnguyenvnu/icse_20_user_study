private JComponent createSymbolTableResultPanel(){
  symbolTableTreeWidget.setCellRenderer(createNoImageTreeCellRenderer());
  return new JScrollPane(symbolTableTreeWidget);
}
