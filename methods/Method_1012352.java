public SkinEntity cover(SkinBean bean){
  List<SkinEntity> list=UserDBManager.getInstance().getDaoSession().getSkinEntityDao().queryBuilder().where(SkinEntityDao.Properties.Url.eq(bean.getUrl())).build().list();
  if (list.size() > 0) {
    list.get(0).setImageList(bean.getImageList());
    list.get(0).setTitle(bean.getTitle());
    UserDBManager.getInstance().getDaoSession().getSkinEntityDao().update(list.get(0));
    return list.get(0);
  }
 else {
    SkinEntity skinEntity=new SkinEntity();
    skinEntity.setTitle(bean.getTitle());
    skinEntity.setImageList(bean.getImageList());
    skinEntity.setHasSelected(false);
    skinEntity.setUrl(bean.getUrl());
    UserDBManager.getInstance().getDaoSession().getSkinEntityDao().insert(skinEntity);
    return skinEntity;
  }
}
