public MergeTool.ToolbarComponents init(){
  MergeTool.ToolbarComponents components=new MergeTool.ToolbarComponents();
  components.toolbarActions=myPanel.getToolbarActions();
  components.closeHandler=new BooleanGetter(){
    public boolean get(){
      return allowCancel();
    }
  }
;
  return components;
}
