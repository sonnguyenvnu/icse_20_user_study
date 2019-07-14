public void contributionRemoved(final Contribution contribution){
  DetailPanel panel=panelByContribution.get(contribution);
  if (panel != null) {
    remove(panel);
    panelByContribution.remove(contribution);
  }
  model.fireTableDataChanged();
  updateColors();
  updateUI();
}
