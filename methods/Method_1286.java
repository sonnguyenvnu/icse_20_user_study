@Override protected VolleyDraweeController obtainController(){
  DraweeController oldController=getOldController();
  VolleyDraweeController controller;
  String controllerId=generateUniqueControllerId();
  if (oldController instanceof VolleyDraweeController) {
    controller=(VolleyDraweeController)oldController;
  }
 else {
    controller=mVolleyDraweeControllerFactory.newController();
  }
  controller.initialize(obtainDataSourceSupplier(controller,controllerId),controllerId,getCallerContext());
  return controller;
}
