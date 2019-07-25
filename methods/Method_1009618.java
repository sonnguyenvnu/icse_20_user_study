@Override public void init(Action action,ActionArgumentValue[] presetInputValues){
  inputArgumentsTable=new ActionArgumentTable(action,true){
    @Override protected void onExpandText(    String text){
      presenter.onExpandText(text);
    }
  }
;
  outputArgumentsTable=new ActionArgumentTable(action,false){
    @Override protected void onExpandText(    String text){
      presenter.onExpandText(text);
    }
  }
;
  if (presetInputValues != null && presetInputValues.length > 0)   inputArgumentsTable.getArgumentValuesModel().setValues(presetInputValues);
  inputArgumentsScrollPane=new JScrollPane(inputArgumentsTable);
  outputArgumentsScrollPane=new JScrollPane(outputArgumentsTable);
  JPanel mainPanel=new JPanel(new BorderLayout());
  mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
  mainPanel.add(invocationToolBar,BorderLayout.NORTH);
  if (action.hasInputArguments() && action.hasOutputArguments()) {
    splitPane.setTopComponent(inputArgumentsScrollPane);
    splitPane.setBottomComponent(outputArgumentsScrollPane);
    splitPane.setResizeWeight(0.5);
    mainPanel.add(splitPane,BorderLayout.CENTER);
  }
 else   if (action.hasInputArguments()) {
    mainPanel.add(inputArgumentsScrollPane,BorderLayout.CENTER);
  }
 else   if (action.hasOutputArguments()) {
    mainPanel.add(outputArgumentsScrollPane,BorderLayout.CENTER);
  }
  add(mainPanel);
  setTitle("Invoking Action: " + action.getName());
  setPreferredSize(new Dimension(450,(action.getArguments().length * 40) + 120));
  pack();
  setVisible(true);
}
