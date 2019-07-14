void onRowSelected(int index){
  selectedIndex=index;
  DetailItemModel model=DetailItemModel.values()[index];
  ChildController controller=new ChildController(model.detail,model.backgroundColor,true);
  if (twoPaneView) {
    getChildRouter(detailContainer).setRoot(RouterTransaction.with(controller));
  }
 else {
    getRouter().pushController(RouterTransaction.with(controller).pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
  }
}
