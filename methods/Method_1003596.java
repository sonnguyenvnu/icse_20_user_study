@Override public synchronized void init(ProcessingEnvironment processingEnv){
  mFiler=processingEnv.getFiler();
  mElements=processingEnv.getElementUtils();
  mLog=new Logger(processingEnv.getMessager());
  mContext=TypeName.get(mElements.getTypeElement(Constants.CONTEXT_TYPE).asType());
  mOnRegisterType=TypeName.get(mElements.getTypeElement(Constants.ON_REGISTER_TYPE).asType());
  mRegisterType=TypeName.get(mElements.getTypeElement(Constants.REGISTER_TYPE).asType());
  mResolver=TypeName.get(mElements.getTypeElement(Constants.RESOLVER_TYPE).asType());
  mString=TypeName.get(String.class);
}
