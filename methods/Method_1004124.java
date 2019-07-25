@Override protected void head(final HTMLElement head) throws IOException {
  super.head(head);
  head.script(context.getResources().getLink(folder,Resources.SORT_SCRIPT));
}
