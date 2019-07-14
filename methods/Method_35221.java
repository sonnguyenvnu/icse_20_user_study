/** 
 * Used to serialize this transaction into a Bundle
 */
@NonNull public Bundle saveInstanceState(){
  Bundle bundle=new Bundle();
  bundle.putBundle(KEY_VIEW_CONTROLLER_BUNDLE,controller.saveInstanceState());
  if (pushControllerChangeHandler != null) {
    bundle.putBundle(KEY_PUSH_TRANSITION,pushControllerChangeHandler.toBundle());
  }
  if (popControllerChangeHandler != null) {
    bundle.putBundle(KEY_POP_TRANSITION,popControllerChangeHandler.toBundle());
  }
  bundle.putString(KEY_TAG,tag);
  bundle.putInt(KEY_INDEX,transactionIndex);
  bundle.putBoolean(KEY_ATTACHED_TO_ROUTER,attachedToRouter);
  return bundle;
}
