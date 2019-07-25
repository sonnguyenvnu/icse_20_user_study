private static void svg(PresentationMLPackage presentationMLPackage,ResolvedLayout layout,javax.xml.transform.Result result,SvgSettings settings) throws Exception {
  SvgConversionContext context=null;
  org.w3c.dom.Document doc=XmlUtils.marshaltoW3CDomDocument(layout.getShapeTree(),Context.jcPML,"http://schemas.openxmlformats.org/presentationml/2006/main","spTree",GroupShape.class);
  if (settings == null) {
    settings=new SvgSettings();
  }
  if ((settings.getImageDirPath() == null) && (imageDirPath != null)) {
    settings.setImageDirPath(imageDirPath);
  }
  context=new SvgConversionContext(settings,presentationMLPackage,layout);
  org.docx4j.XmlUtils.transform(doc,xslt,context.getXsltParameters(),result);
}
