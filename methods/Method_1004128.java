private void bar(final HTMLElement td,final int count,final String image,final Resources resources,final ReportOutputFolder base) throws IOException {
  final int width=count * WIDTH / max;
  if (width > 0) {
    td.img(resources.getLink(base,image),width,10,integerFormat.format(count));
  }
}
