public void contributionChanged(final Contribution oldContrib,final Contribution newContrib){
  DetailPanel panel=panelByContribution.get(oldContrib);
  if (panel == null) {
    contributionAdded(newContrib);
  }
 else {
    panelByContribution.remove(oldContrib);
    panel.setContribution(newContrib);
    panelByContribution.put(newContrib,panel);
    model.fireTableDataChanged();
  }
}
