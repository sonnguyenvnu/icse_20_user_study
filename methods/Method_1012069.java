/** 
 * Single point to dispatch auto scrolling event. By default, delegates to  {@link MPSTreeNode#autoscroll()} ()}
 */
protected void autoscroll(@NotNull MPSTreeNode nodeToClick){
  nodeToClick.autoscroll();
}
