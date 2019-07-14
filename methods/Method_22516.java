/** 
 * Updates the colors of all library panels that are visible.
 */
protected void updateColors(){
  int count=0;
  for (  Entry<Contribution,DetailPanel> entry : panelByContribution.entrySet()) {
    DetailPanel panel=entry.getValue();
    Border border=BorderFactory.createEmptyBorder(1,1,1,1);
    if (panel.isVisible()) {
      boolean oddRow=count % 2 == 1;
      Color bgColor=null;
      Color fgColor=UIManager.getColor("List.foreground");
      if (panel.isSelected()) {
        bgColor=UIManager.getColor("List.selectionBackground");
        fgColor=UIManager.getColor("List.selectionForeground");
        border=UIManager.getBorder("List.focusCellHighlightBorder");
      }
 else       if (Platform.isMacOS()) {
        border=oddRow ? UIManager.getBorder("List.oddRowBackgroundPainter") : UIManager.getBorder("List.evenRowBackgroundPainter");
      }
 else {
        bgColor=oddRow ? new Color(219,224,229) : new Color(241,241,241);
      }
      panel.setForeground(fgColor);
      if (bgColor != null) {
        panel.setBackground(bgColor);
      }
      count++;
    }
    panel.setBorder(border);
  }
}
