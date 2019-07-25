public static RegisterThirdStepFragment create(String mobile){
  RegisterThirdStepFragment fragment=new RegisterThirdStepFragment();
  if (!TextUtils.isEmpty(mobile)) {
    Bundle bundle=new Bundle();
    bundle.putString(Display.PARAM_OBJ,mobile);
    fragment.setArguments(bundle);
  }
  return fragment;
}
