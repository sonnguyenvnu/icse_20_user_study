private HTMLElement span(final HTMLElement parent,final String id,final String style1,final String style2,final String title,final ICounter branches) throws IOException {
  final HTMLElement span=parent.span(style1 + " " + style2,id);
  final Integer missed=Integer.valueOf(branches.getMissedCount());
  final Integer total=Integer.valueOf(branches.getTotalCount());
  span.attr("title",String.format(locale,title,missed,total));
  return span;
}
