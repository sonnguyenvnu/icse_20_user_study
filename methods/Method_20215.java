/** 
 * Create and return a new instance of a view for this model. By default a view is created by inflating the layout resource.
 */
protected View buildView(@NonNull ViewGroup parent){
  return LayoutInflater.from(parent.getContext()).inflate(getLayout(),parent,false);
}
