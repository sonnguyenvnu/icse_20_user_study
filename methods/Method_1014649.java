public XWPFTemplate render(Object model){
  RenderFactory.getRender(model,config.getElMode()).render(this);
  return this;
}
