/** 
 * Set up the panel for the help tab and load its contents from a file.
 * @return The component containing the help tab and its contents
 */
public JComponent build(){
  FormLayout layout=new FormLayout("left:pref, 0:grow","pref, fill:default:grow");
  PanelBuilder builder=new PanelBuilder(layout);
  builder.opaque(true);
  CellConstraints cc=new CellConstraints();
  editorPane=new JEditorPane();
  editorPane.setEditable(false);
  editorPane.setContentType("text/html");
  editorPane.setBackground(Color.WHITE);
  HTMLEditorKit editorKit=new HTMLEditorKit();
  StyleSheet styleSheet=((HTMLDocument)editorKit.createDefaultDocument()).getStyleSheet();
  buildStyleSheet(styleSheet);
  editorKit.setStyleSheet(styleSheet);
  editorPane.setEditorKit(editorKit);
  updateContents();
  editorPane.addHyperlinkListener(new HyperlinkListener(){
    @Override public void hyperlinkUpdate(    HyperlinkEvent event){
      try {
        if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
          String urlString=event.getURL().toExternalForm();
          if (urlString.startsWith("http://") || urlString.startsWith("https://") || urlString.startsWith("ftp://")) {
            URI uri=new URI(urlString);
            Desktop.getDesktop().browse(uri);
          }
 else {
            editorPane.setPage(event.getURL());
          }
        }
      }
 catch (      IOException|URISyntaxException e) {
        LOGGER.debug("Caught exception",e);
      }
    }
  }
);
  JScrollPane pane=new JScrollPane(editorPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  pane.setPreferredSize(new Dimension(500,400));
  pane.setBorder(BorderFactory.createEmptyBorder());
  builder.add(pane,cc.xy(2,2));
  return builder.getPanel();
}
