@Override protected void onUnmount(ComponentContext context,Object mountedContent){
  final MatrixDrawable<T> matrixDrawable=(MatrixDrawable<T>)mountedContent;
  matrixDrawable.unmount();
}
