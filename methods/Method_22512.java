public void contributionAdded(final Contribution contribution){
  if (!panelByContribution.containsKey(contribution)) {
    DetailPanel newPanel=new DetailPanel(this);
    panelByContribution.put(contribution,newPanel);
    newPanel.setContribution(contribution);
    add(newPanel);
    model.fireTableDataChanged();
    updateColors();
  }
}
