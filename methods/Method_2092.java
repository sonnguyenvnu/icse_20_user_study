@Override public void showDuration(long startNs){
  final float deltaMs=startNs / 1e6f;
  final String message=String.format((Locale)null,"Duration: %.1f ms",deltaMs);
  getActivity().runOnUiThread(new Runnable(){
    @Override public void run(){
      Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
  }
);
}
