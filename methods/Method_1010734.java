@Override public JButton visit(final ActionItem actionItem){
  return new MyButton(actionItem,new AbstractAction(actionItem.getLabelText("")){
    @Override public void actionPerformed(    ActionEvent e){
      executeFinalChoice(myStep,actionItem);
    }
  }
);
}
