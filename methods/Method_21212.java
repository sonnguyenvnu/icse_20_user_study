public void setShouldShowOnboardingView(final boolean shouldShowOnboardingView){
  if (shouldShowOnboardingView) {
    setSection(SECTION_ONBOARDING_VIEW,Collections.singletonList(null));
  }
 else {
    setSection(SECTION_ONBOARDING_VIEW,Collections.emptyList());
  }
  notifyDataSetChanged();
}
