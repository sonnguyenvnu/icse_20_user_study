@Override public void contributionChanged(final Contribution oldContrib,final Contribution newContrib){
  DetailPanel panel=panelByContribution.get(oldContrib);
  if (panel == null) {
    contributionAdded(newContrib);
  }
 else   if (newContrib.isInstalled()) {
    panelByContribution.remove(oldContrib);
  }
  model.fireTableDataChanged();
}
