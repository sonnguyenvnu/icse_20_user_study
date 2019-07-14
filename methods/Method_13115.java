protected void onTypeSelected(TriConsumer<Point,Collection<Container.Entry>,String> selectedTypeCallback){
  SwingUtil.invokeLater(() -> {
    int index=openTypeList.getSelectedIndex();
    if (index != -1) {
      OpenTypeListCellBean selectedCellBean=(OpenTypeListCellBean)openTypeList.getModel().getElementAt(index);
      Point listLocation=openTypeList.getLocationOnScreen();
      Rectangle cellBound=openTypeList.getCellBounds(index,index);
      Point leftBottom=new Point(listLocation.x + cellBound.x,listLocation.y + cellBound.y + cellBound.height);
      selectedTypeCallback.accept(leftBottom,selectedCellBean.entries,selectedCellBean.typeName);
    }
  }
);
}
