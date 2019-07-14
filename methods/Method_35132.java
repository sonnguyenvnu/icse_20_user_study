/** 
 * Retrieves the child  {@link Router} for the given container/tag combination. Note that multiplerouters should not exist in the same container unless a lot of care is taken to maintain order between them. Avoid using the same container unless you have a great reason to do so (ex: ViewPagers). The only time this method will return  {@code null} is when the child router does not exist priorto calling this method and the createIfNeeded parameter is set to false.
 * @param container The ViewGroup that hosts the child Router
 * @param tag The router's tag or {@code null} if none is needed
 * @param createIfNeeded If true, a router will be created if one does not yet exist. Else {@code null} will be returned in this case.
 */
@Nullable public final Router getChildRouter(@NonNull ViewGroup container,@Nullable String tag,boolean createIfNeeded){
  @IdRes final int containerId=container.getId();
  ControllerHostedRouter childRouter=null;
  for (  ControllerHostedRouter router : childRouters) {
    if (router.getHostId() == containerId && TextUtils.equals(tag,router.getTag())) {
      childRouter=router;
      break;
    }
  }
  if (childRouter == null) {
    if (createIfNeeded) {
      childRouter=new ControllerHostedRouter(container.getId(),tag);
      childRouter.setHost(this,container);
      childRouters.add(childRouter);
      if (isPerformingExitTransition) {
        childRouter.setDetachFrozen(true);
      }
    }
  }
 else   if (!childRouter.hasHost()) {
    childRouter.setHost(this,container);
    childRouter.rebindIfNeeded();
  }
  return childRouter;
}
