private static void check(){
  if (sToast == null) {
    throw new IllegalStateException("you must call ToastUtil.init(context) first");
  }
}
