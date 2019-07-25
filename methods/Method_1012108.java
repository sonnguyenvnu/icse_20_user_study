private void init(RefactoringViewAction refactoringViewAction,SearchResults searchResults,SearchTask searchTask){
  myRefactoringViewAction=refactoringViewAction;
  mySearchResults=searchResults;
  if (mySearchResults == null) {
    throw new IllegalArgumentException("search result is null");
  }
  myPanel=new JPanel(new BorderLayout());
  myUsagesView=new UsagesView(myProject,new ViewOptions());
  List<AnAction> actions=ListSequence.fromList(new ArrayList<AnAction>());
  if (searchTask != null) {
    UsagesView.RerunAction rerunAction=new UsagesView.RerunAction(myUsagesView,"Run search again");
    rerunAction.setRunOptions(searchTask);
    ListSequence.fromList(actions).addElement(rerunAction);
  }
  ListSequence.fromList(actions).addElement(new AnAction("Close","",AllIcons.Actions.Cancel){
    public void actionPerformed(    @NotNull AnActionEvent p0){
      RefactoringViewItemImpl.this.close();
    }
  }
);
  myUsagesView.setActions(actions);
  myUsagesView.setContents(searchResults);
  myButtonsPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
  myDoRefactorButton=new JButton(new AbstractAction("Do Refactor"){
    @Override public void actionPerformed(    ActionEvent e){
      doRefactor();
    }
  }
);
  myDoRefactorButton.addKeyListener(new KeyAdapter(){
    @Override public void keyTyped(    KeyEvent e){
      if (e.getKeyChar() == '\n') {
        doRefactor();
      }
    }
  }
);
  myButtonsPanel.add(myDoRefactorButton);
  myCancelButton=new JButton(new AbstractAction("Cancel"){
    @Override public void actionPerformed(    ActionEvent e){
      close();
    }
  }
);
  myButtonsPanel.add(myCancelButton);
  myPanel.add(myUsagesView.getComponent(),BorderLayout.CENTER);
  myPanel.add(myButtonsPanel,BorderLayout.SOUTH);
  final FocusTraversalPolicy ftp=myPanel.getFocusTraversalPolicy();
  myPanel.setFocusTraversalPolicy(new RefactoringViewItemImpl.MyFocusTraversalPolicy(ftp));
}
