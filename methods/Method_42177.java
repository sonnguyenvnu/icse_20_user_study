private void showErrors(List<ErrorCause> errors){
  ErrorCauseAdapter adapter=new ErrorCauseAdapter(getContext(),errors);
  rvErrors.setLayoutManager(new LinearLayoutManager(getContext()));
  rvErrors.setAdapter(adapter);
  rvErrors.setVisibility(View.VISIBLE);
  progressBar.setVisibility(View.GONE);
}
