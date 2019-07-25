@Override protected void head(final HTMLElement head) throws IOException {
  super.head(head);
  head.link("stylesheet",context.getResources().getLink(folder,Resources.PRETTIFY_STYLESHEET),"text/css");
  head.script(context.getResources().getLink(folder,Resources.PRETTIFY_SCRIPT));
}
