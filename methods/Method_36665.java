/** 
 * set compiled binary data after engine has been setup, used when load template data dynamically
 * @param type
 * @param data
 */
public void registerVirtualViewTemplate(String type,byte[] data){
  BaseCellBinderResolver baseCellBinderResolver=getService(BaseCellBinderResolver.class);
  BaseCardBinderResolver baseCardBinderResolver=getService(BaseCardBinderResolver.class);
  if (baseCellBinderResolver != null && baseCardBinderResolver != null) {
    CardResolver cardResolver=baseCardBinderResolver.getDelegate();
    MVHelper mMVHelper=getService(MVHelper.class);
    if (cardResolver != null && mMVHelper != null) {
      baseCellBinderResolver.register(type,new BaseCellBinder(type,mMVHelper));
      cardResolver.register(type,VVCard.class);
      setVirtualViewTemplate(data);
    }
  }
}
