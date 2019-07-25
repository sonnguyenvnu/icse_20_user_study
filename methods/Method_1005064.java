protected void transform(final Element element,final ElementTransformer transformer){
  if (null != transformer) {
    transformer.apply(element);
  }
}
