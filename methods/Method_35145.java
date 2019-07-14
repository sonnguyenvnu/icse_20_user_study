private void restoreChildControllerHosts(){
  for (  ControllerHostedRouter childRouter : childRouters) {
    if (!childRouter.hasHost()) {
      View containerView=view.findViewById(childRouter.getHostId());
      if (containerView != null && containerView instanceof ViewGroup) {
        childRouter.setHost(this,(ViewGroup)containerView);
        childRouter.rebindIfNeeded();
      }
    }
  }
}
