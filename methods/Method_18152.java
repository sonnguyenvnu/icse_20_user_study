/** 
 * Crash if the given node has context specific style set. 
 */
@Override public void assertContextSpecificStyleNotSet(){
  List<CharSequence> errorTypes=null;
  if ((mPrivateFlags & PFLAG_ALIGN_SELF_IS_SET) != 0L) {
    errorTypes=addOrCreateList(errorTypes,"alignSelf");
  }
  if ((mPrivateFlags & PFLAG_POSITION_TYPE_IS_SET) != 0L) {
    errorTypes=addOrCreateList(errorTypes,"positionType");
  }
  if ((mPrivateFlags & PFLAG_FLEX_IS_SET) != 0L) {
    errorTypes=addOrCreateList(errorTypes,"flex");
  }
  if ((mPrivateFlags & PFLAG_FLEX_GROW_IS_SET) != 0L) {
    errorTypes=addOrCreateList(errorTypes,"flexGrow");
  }
  if ((mPrivateFlags & PFLAG_MARGIN_IS_SET) != 0L) {
    errorTypes=addOrCreateList(errorTypes,"margin");
  }
  if (errorTypes != null) {
    final CharSequence errorStr=TextUtils.join(", ",errorTypes);
    final ComponentsLogger logger=getContext().getLogger();
    if (logger != null) {
      logger.emitMessage(WARNING,"You should not set " + errorStr + " to a root layout in " + getTailComponent().getClass().getSimpleName());
    }
  }
}
