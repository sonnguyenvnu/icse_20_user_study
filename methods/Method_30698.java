private void enable(){
  if (!mInjected) {
    ScalpelUtils.inject(getActivity());
    mInjected=true;
  }
  ScalpelUtils.setEnabled(getActivity(),true);
}
