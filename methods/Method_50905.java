private JPanel makeProgressPanel(){
  JPanel progressPanel=new JPanel();
  final double[] weights={0.0,0.8,0.4,0.2};
  GridBagHelper helper=new GridBagHelper(progressPanel,weights);
  helper.addLabel("Tokenizing files:");
  helper.add(tokenizingFilesBar,3);
  helper.nextRow();
  helper.addLabel("Phase:");
  helper.add(phaseLabel);
  helper.addLabel("Time elapsed:");
  helper.add(timeField);
  helper.nextRow();
  progressPanel.setBorder(BorderFactory.createTitledBorder("Progress"));
  return progressPanel;
}
