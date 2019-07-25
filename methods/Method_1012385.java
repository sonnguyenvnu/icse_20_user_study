public void update(String attrName,int resId,Context context){
  setResId(resId);
  setAttrName(attrName);
  setTypeName(context.getResources().getResourceTypeName(resId));
  setResName(context.getResources().getResourceName(resId));
  CommonLogger.e(toString());
}
