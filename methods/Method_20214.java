/** 
 * Get the view type to associate with this model in the recyclerview. For models that use a layout resource, the view type is simply the layout resource value by default. <p> If this returns 0 Epoxy will assign a unique view type for this model at run time.
 * @see androidx.recyclerview.widget.RecyclerView.Adapter#getItemViewType(int)
 */
protected int getViewType(){
  return getLayout();
}
