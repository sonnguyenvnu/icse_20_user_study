@Override protected TextField create(ListView<AnnotationNode> view){
  TextField annoDesc=new TextField();
  annoDesc.setTooltip(new Tooltip(Lang.get("ui.bean.class.annotations.desc.tooltip")));
  annoDesc.setOnAction((e) -> add(annoDesc,view));
  return annoDesc;
}
