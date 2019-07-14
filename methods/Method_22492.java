public void setContribution(Contribution contrib){
  this.contrib=contrib;
  if (contrib.isSpecial()) {
    JLabel iconLabel=new JLabel(foundationIcon);
    iconLabel.setBorder(new EmptyBorder(4,7,7,7));
    iconLabel.setVerticalAlignment(SwingConstants.TOP);
    add(iconLabel,BorderLayout.WEST);
  }
  Font boldFont=ManagerFrame.SMALL_BOLD;
  String fontFace="<font face=\"" + boldFont.getName() + "\">";
  StringBuilder desc=new StringBuilder();
  desc.append("<html><body>" + fontFace);
  if (contrib.getUrl() == null) {
    desc.append(contrib.getName());
  }
 else {
    desc.append("<a href=\"" + contrib.getUrl() + "\">" + contrib.getName() + "</a>");
  }
  desc.append("</font> ");
  String prettyVersion=contrib.getPrettyVersion();
  if (prettyVersion != null) {
    desc.append(prettyVersion);
  }
  desc.append(" <br/>");
  String authorList=contrib.getAuthorList();
  if (authorList != null && !authorList.isEmpty()) {
    desc.append(toHtmlLinks(contrib.getAuthorList()));
  }
  desc.append("<br/><br/>");
  if (contrib.isDeletionFlagged()) {
    desc.append(REMOVE_RESTART_MESSAGE);
  }
 else   if (contrib.isRestartFlagged()) {
    desc.append(INSTALL_RESTART_MESSAGE);
  }
 else   if (contrib.isUpdateFlagged()) {
    desc.append(UPDATE_RESTART_MESSAGE);
  }
 else {
    String sentence=contrib.getSentence();
    if (sentence == null || sentence.isEmpty()) {
      sentence=String.format("<i>%s</i>",Language.text("contrib.errors.description_unavailable"));
    }
 else {
      sentence=sanitizeHtmlTags(sentence);
      sentence=toHtmlLinks(sentence);
    }
    desc.append(sentence);
  }
  long lastUpdatedUTC=contrib.getLastUpdated();
  if (lastUpdatedUTC != 0) {
    DateFormat dateFormatter=DateFormat.getDateInstance(DateFormat.MEDIUM);
    Date lastUpdatedDate=new Date(lastUpdatedUTC);
    if (prettyVersion != null) {
      desc.append(", ");
    }
    desc.append("Last Updated on " + dateFormatter.format(lastUpdatedDate));
  }
  desc.append("</body></html>");
  description=desc.toString();
  descriptionPane.setText(description);
  if (contribListing.hasUpdates(contrib) && contrib.isCompatible(Base.getRevision())) {
    StringBuilder versionText=new StringBuilder();
    versionText.append("<html><body><i>");
    if (contrib.isUpdateFlagged() || contrib.isDeletionFlagged()) {
      ;
    }
 else {
      String latestVersion=contribListing.getLatestPrettyVersion(contrib);
      if (latestVersion != null) {
        versionText.append("New version (" + latestVersion + ") available.");
      }
 else {
        versionText.append("New version available.");
      }
    }
    versionText.append("</i></body></html>");
    notificationLabel.setText(versionText.toString());
    notificationLabel.setVisible(true);
  }
 else {
    notificationLabel.setText("");
    notificationLabel.setVisible(false);
  }
  updateButton.setEnabled(true);
  if (contrib != null) {
    updateButton.setVisible((contribListing.hasUpdates(contrib) && !contrib.isUpdateFlagged() && !contrib.isDeletionFlagged()) || updateInProgress);
  }
  if (contrib.isDeletionFlagged()) {
    installRemoveButton.setText(undoText);
  }
 else   if (contrib.isInstalled()) {
    installRemoveButton.setText(removeText);
    installRemoveButton.setVisible(true);
    installRemoveButton.setEnabled(!contrib.isUpdateFlagged());
    reorganizePaneComponents();
  }
 else {
    installRemoveButton.setText(installText);
  }
  contextMenu.removeAll();
  if (contrib.isInstalled()) {
    contextMenu.add(openFolder);
    setComponentPopupMenu(contextMenu);
  }
 else {
    setComponentPopupMenu(null);
  }
  if (!contrib.isCompatible(Base.getRevision())) {
    blurContributionPanel(this);
  }
}
