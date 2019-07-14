/** 
 * register item render by virtual view* @param type
 */
public <V extends View>void registerVirtualView(String type){
  mDefaultCellBinderResolver.register(type,new BaseCellBinder<>(type,mMVHelper));
  registerCard(type,VVCard.class);
}
