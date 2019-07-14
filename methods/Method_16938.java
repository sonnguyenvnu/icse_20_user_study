private String getJavaDoc(){
  StringBuilder doc=new StringBuilder(200);
  doc.append("<em>WARNING: GENERATED CODE</em>\n\n" + "A cache entry that provides the following features:\n<ul>");
  for (  Feature feature : context.generateFeatures) {
    String name=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,feature.name());
    doc.append("\n  <li>").append(name);
  }
  for (  Feature feature : context.parentFeatures) {
    String name=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,feature.name());
    doc.append("\n  <li>").append(name).append(" (inherited)");
  }
  doc.append("\n</ul>\n\n@author ben.manes@gmail.com (Ben Manes)\n");
  return doc.toString();
}
