public void setup(ToolWindowEx toolWindow){
  ContentManager contentManager=toolWindow.getContentManager();
  Content content=contentManager.getFactory().createContent(new Symfony2WebProfilerForm(this.project).createComponent(),null,true);
  contentManager.addContent(content);
  contentManager.setSelectedContent(content,true);
}
