protected void init(){
  myPanel.setSplitterProportionKey(getClass().getName() + "ModelTreeSplitter");
  myMergeTree=new MergeModelsPanel.MergeModelsTree(myProjectRepository);
  myPanel.setFirstComponent(ScrollPaneFactory.createScrollPane(myMergeTree));
  myPanel.setSecondComponent(myNoRootPanel);
  myGoToNeighbourRootActions=new MergeModelsPanel.MyGoToNeighbourRootActions();
  myGoToNeighbourRootActions.previous().registerCustomShortcutSet(GoToNeighbourRootActions.PREV_ROOT_SHORTCUT,this);
  myGoToNeighbourRootActions.next().registerCustomShortcutSet(GoToNeighbourRootActions.NEXT_ROOT_SHORTCUT,this);
  this.add(myPanel,BorderLayout.CENTER);
  final Dimension size=DimensionService.getInstance().getSize(getDimensionServiceKey());
  if (size == null) {
    this.setPreferredSize(new Dimension(500,450));
  }
}
