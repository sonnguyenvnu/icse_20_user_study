/** 
 * Setup click listener to cancel button click for various intent types
 */
private void cancelBroadcast(final Intent intent){
  mCancelButton.setOnClickListener(v -> {
    Toast.makeText(getActivity(),getResources().getString(R.string.stopping),Toast.LENGTH_LONG).show();
    getActivity().sendBroadcast(intent);
    mProgressTypeText.setText(getResources().getString(R.string.cancelled));
    mProgressSpeedText.setText("");
    mProgressFileText.setText("");
    mProgressBytesText.setText("");
    mProgressFileNameText.setText("");
    mProgressTypeText.setTextColor(Utils.getColor(getContext(),android.R.color.holo_red_light));
  }
);
}
