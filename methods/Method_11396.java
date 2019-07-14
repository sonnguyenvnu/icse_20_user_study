private BaseViewHolder createBaseViewHolder(ViewGroup parent,int layoutResId){
  return new BaseViewHolder(inflateItemView(layoutResId,parent));
}
