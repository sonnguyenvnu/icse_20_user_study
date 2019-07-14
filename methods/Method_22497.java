/** 
 * Should be called whenever this component is selected (clicked on) or unselected, even if it is already selected.
 */
public void setSelected(boolean isSelected){
  enableHyperlinks=alreadySelected;
  if (contrib != null) {
    updateButton.setVisible((contribListing.hasUpdates(contrib) && !contrib.isUpdateFlagged() && !contrib.isDeletionFlagged()) || updateInProgress);
    updateButton.setEnabled(!contribListing.hasListDownloadFailed());
  }
  installRemoveButton.setVisible(isSelected() || installRemoveButton.getText().equals(Language.text("contrib.remove")) || updateInProgress);
  installRemoveButton.setEnabled(installRemoveButton.getText().equals(Language.text("contrib.remove")) || !contribListing.hasListDownloadFailed());
  reorganizePaneComponents();
  setSelectionStyle(descriptionPane,isSelected());
  alreadySelected=isSelected();
}
