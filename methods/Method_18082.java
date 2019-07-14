@Override public void addWorkingRanges(List<WorkingRangeContainer.Registration> registrations){
  if (mWorkingRangeRegistrations == null) {
    mWorkingRangeRegistrations=new ArrayList<>(registrations.size());
  }
  mWorkingRangeRegistrations.addAll(registrations);
}
