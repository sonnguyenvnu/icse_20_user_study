private void rebuild(){
  removeAll();
  setLayout(new GridBagLayout());
  GridBagConstraints gridBagConstraints=new GridBagConstraints();
  gridBagConstraints.gridx=0;
  gridBagConstraints.gridy=0;
  gridBagConstraints.weightx=1;
  gridBagConstraints.weighty=0;
  gridBagConstraints.anchor=GridBagConstraints.NORTH;
  gridBagConstraints.fill=GridBagConstraints.BOTH;
  JButton debugCurrentRootButton=new JButton(new AbstractAction("Debug Current Root"){
    public void actionPerformed(    ActionEvent e){
      com.intellij.openapi.project.Project project=ProjectHelper.toIdeaProject(myProject);
      Editor currentEditor=EditorsHelper.getSelectedEditors(FileEditorManager.getInstance(project)).get(0);
      if (currentEditor != null) {
        EditorComponent editorComponent=currentEditor.getCurrentEditorComponent();
        if (editorComponent != null) {
          final SNode currentRoot=editorComponent.getEditedNode();
          debugRoot(currentRoot);
        }
      }
    }
  }
);
  JPanel upperPanel=new JPanel();
  upperPanel.setLayout(new GridBagLayout());
  GridBagConstraints upperPanelConstraints=new GridBagConstraints();
  upperPanelConstraints.gridx=0;
  upperPanelConstraints.gridy=0;
  upperPanelConstraints.weightx=0;
  upperPanelConstraints.weighty=0;
  upperPanelConstraints.anchor=GridBagConstraints.NORTHWEST;
  upperPanel.add(debugCurrentRootButton,upperPanelConstraints);
  String text="no info collected";
  upperPanelConstraints.gridx=1;
  upperPanel.add(new JLabel(text));
  upperPanelConstraints.weightx=1;
  upperPanelConstraints.gridx=2;
  upperPanel.add(new JPanel(),upperPanelConstraints);
  add(upperPanel,gridBagConstraints);
  JPanel innerPanel=new JPanel(new GridBagLayout());
  int y=0;
  GridBagConstraints innerConstraints=new GridBagConstraints();
  if (myNodeToSliceWith != null) {
    innerConstraints.gridy=y;
    y++;
    innerConstraints.weighty=0;
    innerConstraints.weightx=0;
    innerConstraints.fill=GridBagConstraints.NONE;
    innerConstraints.anchor=GridBagConstraints.NORTHWEST;
    innerConstraints.gridx=0;
    innerPanel.add(new JLabel("initial type: "),innerConstraints);
    innerConstraints.gridx=1;
    innerConstraints.gridwidth=GridBagConstraints.REMAINDER;
    SNodeTree initialTypeTree=new SNodeTree(null);
    innerPanel.add(initialTypeTree,innerConstraints);
    initialTypeTree.rebuildNow();
    innerConstraints.gridwidth=1;
  }
  innerConstraints.gridy=y;
  innerConstraints.weighty=1;
  JPanel gauge=new JPanel();
  gauge.setBackground(Color.WHITE);
  innerPanel.add(gauge,innerConstraints);
  innerPanel.setBackground(Color.WHITE);
  JScrollPane scrollPane=ScrollPaneFactory.createScrollPane(innerPanel);
  scrollPane.setBackground(Color.WHITE);
  gridBagConstraints.weighty=1;
  gridBagConstraints.gridy=1;
  add(scrollPane,gridBagConstraints);
}
