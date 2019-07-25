private void body(final HTMLElement body) throws IOException {
  body.attr("onload",getOnload());
  final HTMLElement navigation=body.div(Styles.BREADCRUMB);
  navigation.attr("id","breadcrumb");
  infoLinks(navigation.span(Styles.INFO));
  breadcrumb(navigation,folder);
  body.h1().text(getLinkLabel());
  content(body);
  footer(body);
}
