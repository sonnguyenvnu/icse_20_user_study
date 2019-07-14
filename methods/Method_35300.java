@Override protected void onViewBound(@NonNull View view){
  super.onViewBound(view);
  View bgView=view.findViewById(R.id.bg_view);
  if (transitionDemo.colorId != 0 && bgView != null) {
    bgView.setBackgroundColor(ContextCompat.getColor(getActivity(),transitionDemo.colorId));
  }
  final int nextIndex=transitionDemo.ordinal() + 1;
  int buttonColor=0;
  if (nextIndex < TransitionDemo.values().length) {
    buttonColor=TransitionDemo.fromIndex(nextIndex).colorId;
  }
  if (buttonColor == 0) {
    buttonColor=TransitionDemo.fromIndex(0).colorId;
  }
  btnNext.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(),buttonColor)));
  tvTitle.setText(transitionDemo.title);
}
