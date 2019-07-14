/** 
 * register cell with custom view class, the model of cell is provided with default type
 * @param type
 * @param viewClz
 * @param < V >
 */
public <V extends View>void registerCell(String type,final @NonNull Class<V> viewClz){
  if (viewHolderMap.get(type) == null) {
    mDefaultCellBinderResolver.register(type,new BaseCellBinder<>(viewClz,mMVHelper));
  }
 else {
    mDefaultCellBinderResolver.register(type,new BaseCellBinder<ViewHolderCreator.ViewHolder,V>(viewHolderMap.get(type),mMVHelper));
  }
  mMVHelper.resolver().register(type,viewClz);
}
