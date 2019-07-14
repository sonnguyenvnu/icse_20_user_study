private void _batchSet(Context context,MethodVisitor mw,boolean flag){
  for (int i=0, size=context.fieldInfoList.length; i < size; ++i) {
    Label notSet_=new Label();
    if (flag) {
      _isFlag(mw,context,i,notSet_);
    }
    FieldInfo fieldInfo=context.fieldInfoList[i];
    _loadAndSet(context,mw,fieldInfo);
    if (flag) {
      mw.visitLabel(notSet_);
    }
  }
}
