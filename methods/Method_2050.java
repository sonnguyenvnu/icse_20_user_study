/** 
 * Utility method which set the size based on the parent and configurations
 * @param parentView The parent View
 * @param draweeView The View to resize
 * @param config The Config object
 */
public static void setConfiguredSize(final View parentView,final View draweeView,final Config config){
  if (parentView != null) {
    if (config.overrideSize) {
      SizeUtil.updateViewLayoutParams(draweeView,config.overridenWidth,config.overridenHeight);
    }
 else {
      int size=SizeUtil.calcDesiredSize(parentView.getContext(),parentView.getWidth(),parentView.getHeight());
      SizeUtil.updateViewLayoutParams(draweeView,size,(int)(size / Const.RATIO));
    }
  }
}
