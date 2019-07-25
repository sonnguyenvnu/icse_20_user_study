public FieldUtil add(String fieldName,String name,Integer fieldId){
  AdminFieldSort adminFieldSort=new AdminFieldSort();
  adminFieldSort.setLabel(label).setIsHide(0).setUserId(userId).setFieldName(fieldName).setName(name).setFieldId(fieldId);
  adminFieldSortList.add(adminFieldSort);
  return this;
}
