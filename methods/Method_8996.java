private void highlightRowInternal(RecyclerListView.IntReturnCallback callback,boolean canHighlightLater){
  if (removeHighlighSelectionRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(removeHighlighSelectionRunnable);
    removeHighlighSelectionRunnable=null;
  }
  RecyclerView.ViewHolder holder=findViewHolderForAdapterPosition(callback.run());
  if (holder != null) {
    positionSelector(holder.getLayoutPosition(),holder.itemView);
    if (selectorDrawable != null) {
      final Drawable d=selectorDrawable.getCurrent();
      if (d instanceof TransitionDrawable) {
        if (onItemLongClickListener != null || onItemClickListenerExtended != null) {
          ((TransitionDrawable)d).startTransition(ViewConfiguration.getLongPressTimeout());
        }
 else {
          ((TransitionDrawable)d).resetTransition();
        }
      }
      if (Build.VERSION.SDK_INT >= 21) {
        selectorDrawable.setHotspot(holder.itemView.getMeasuredWidth() / 2,holder.itemView.getMeasuredHeight() / 2);
      }
    }
    if (selectorDrawable != null && selectorDrawable.isStateful()) {
      if (selectorDrawable.setState(getDrawableStateForSelector())) {
        invalidateDrawable(selectorDrawable);
      }
    }
    AndroidUtilities.runOnUIThread(removeHighlighSelectionRunnable=() -> {
      removeHighlighSelectionRunnable=null;
      pendingHighlightPosition=null;
      if (selectorDrawable != null) {
        Drawable d=selectorDrawable.getCurrent();
        if (d instanceof TransitionDrawable) {
          ((TransitionDrawable)d).resetTransition();
        }
      }
      if (selectorDrawable != null && selectorDrawable.isStateful()) {
        selectorDrawable.setState(StateSet.NOTHING);
      }
    }
,700);
  }
 else   if (canHighlightLater) {
    pendingHighlightPosition=callback;
  }
}
