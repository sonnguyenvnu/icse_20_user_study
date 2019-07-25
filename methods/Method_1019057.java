private void open(ArgumentEditor argEditor){
  if (staged()) {
    return;
  }
  Object[] args=(Object[])getValue();
  ReflectivePropertySheet psHandle=new ReflectivePropertySheet(args[1]){
    protected void setupItems(    Object instance){
      for (      Field field : Reflect.fields(instance.getClass())) {
        if (AccessFlag.isStatic(field.getModifiers())) {
          continue;
        }
        field.setAccessible(true);
        String name=field.getName();
        String group="ui.bean.bsmarg";
        if (name.equals("tag")) {
          getItems().add(new ReflectiveItem(instance,field,group,name){
            @Override protected Class<?> getEditorType(){
              return TagEditor.class;
            }
          }
);
        }
 else {
          getItems().add(new ReflectiveItem(instance,field,group,name));
        }
      }
    }
  }
;
  psHandle.setModeSwitcherVisible(false);
  psHandle.setSearchBoxVisible(false);
  ReflectivePropertySheet psType0=new ReflectivePropertySheet(args[0]){
    protected void setupItems(    Object instance){
      if (instance instanceof Type) {
        getItems().add(new ReflectiveItem(item.getOwner(),item.getField(),"ui.bean.bsmarg","type0"){
          @Override protected Class<?> getEditorType(){
            return Type0Editor.class;
          }
          @Override public Object getValue(){
            return args[0];
          }
          @Override public void setValue(          Object value){
            args[0]=value;
          }
        }
);
      }
    }
  }
;
  psType0.setModeSwitcherVisible(false);
  psType0.setSearchBoxVisible(false);
  ReflectivePropertySheet psType2=new ReflectivePropertySheet(args[2]){
    protected void setupItems(    Object instance){
      if (instance instanceof Type) {
        getItems().add(new ReflectiveItem(item.getOwner(),item.getField(),"ui.bean.bsmarg","type2"){
          @Override protected Class<?> getEditorType(){
            return Type2Editor.class;
          }
          @Override public Object getValue(){
            return args[2];
          }
          @Override public void setValue(          Object value){
            args[2]=value;
          }
        }
);
      }
    }
  }
;
  psType2.setModeSwitcherVisible(false);
  psType2.setSearchBoxVisible(false);
  VBox v=new VBox();
  v.getChildren().add(psHandle);
  v.getChildren().add(psType0);
  v.getChildren().add(psType2);
  setStage("ui.bean.opcode.bsmargs.name",v,600,300);
}
