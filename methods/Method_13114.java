@SuppressWarnings("unchecked") public void updateList(Map<String,Collection<Container.Entry>> map){
  SwingUtil.invokeLater(() -> {
    DefaultListModel model=(DefaultListModel)openTypeList.getModel();
    ArrayList<String> typeNames=new ArrayList<>(map.keySet());
    int index=0;
    typeNames.sort(TYPE_NAME_COMPARATOR);
    model.removeAllElements();
    for (    String typeName : typeNames) {
      if (index < MAX_LINE_COUNT) {
        Collection<Container.Entry> entries=map.get(typeName);
        Container.Entry firstEntry=entries.iterator().next();
        Type type=api.getTypeFactory(firstEntry).make(api,firstEntry,typeName);
        if (type != null) {
          model.addElement(new OpenTypeListCellBean(type.getDisplayTypeName(),type.getDisplayPackageName(),type.getIcon(),entries,typeName));
        }
 else {
          model.addElement(new OpenTypeListCellBean(typeName,entries,typeName));
        }
      }
 else       if (index == MAX_LINE_COUNT) {
        model.addElement(null);
      }
    }
    int count=typeNames.size();
switch (count) {
case 0:
      openTypeMatchLabel.setText("Matching types:");
    break;
case 1:
  openTypeMatchLabel.setText("1 matching type:");
break;
default :
openTypeMatchLabel.setText(count + " matching types:");
}
}
);
}
