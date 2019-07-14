protected boolean isSafe(){
  return getView() != null && getActivity() != null && !getActivity().isFinishing();
}
