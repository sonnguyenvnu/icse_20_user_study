private void onNavigationClicked(String clickedReferenceUniqueStr){
  if (isLocallyNavigable(clickedReferenceUniqueStr)) {
    onLocalNavigationRequest(clickedReferenceUniqueStr);
  }
 else   if (linkProvider.isLinkNavigable(clickedReferenceUniqueStr)) {
    onOutboundNavigationRequest(clickedReferenceUniqueStr);
  }
 else {
    JLabel label=this.mainWindow.getLabel();
    if (label == null)     return;
    String[] linkParts=clickedReferenceUniqueStr.split("\\|");
    if (linkParts.length <= 1) {
      label.setText("Cannot navigate: " + clickedReferenceUniqueStr);
      return;
    }
    String destinationTypeStr=linkParts[1];
    label.setText("Cannot navigate: " + destinationTypeStr.replaceAll("/","."));
  }
}
