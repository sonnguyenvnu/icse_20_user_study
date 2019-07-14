/** 
 * @see #chooseDropTarget(ViewHolder,List,int,int)
 */
protected EpoxyViewHolder chooseDropTarget(EpoxyViewHolder selected,List<EpoxyViewHolder> dropTargets,int curX,int curY){
  return (EpoxyViewHolder)super.chooseDropTarget(selected,(List)dropTargets,curX,curY);
}
