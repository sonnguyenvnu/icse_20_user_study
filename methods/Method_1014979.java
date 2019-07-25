public FieldUtil add(String fieldName,String name){
  AdminFieldSort adminFieldSort=new AdminFieldSort();
  adminFieldSort.setLabel(label).setIsHide(0).setUserId(userId).setFieldName(fieldName).setName(name);
  adminFieldSortList.add(adminFieldSort);
  return this;
}
