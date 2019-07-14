private boolean endChangeAnimationIfNecessary(ChangeInfo changeInfo,ViewHolder item){
  boolean oldItem=false;
  if (changeInfo.newHolder == item) {
    changeInfo.newHolder=null;
  }
 else   if (changeInfo.oldHolder == item) {
    changeInfo.oldHolder=null;
    oldItem=true;
  }
 else {
    return false;
  }
  item.itemView.setAlpha(1);
  item.itemView.setTranslationX(0);
  item.itemView.setTranslationY(0);
  dispatchChangeFinished(item,oldItem);
  return true;
}
