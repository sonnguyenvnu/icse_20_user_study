protected @LayoutRes int layout(final @NonNull SectionRow sectionRow){
  if (sectionRow.section() == SECTION_FUNDING_VIEW) {
    return R.layout.dashboard_funding_view;
  }
 else   if (sectionRow.section() == SECTION_REWARD_STATS_VIEW) {
    return R.layout.dashboard_reward_stats_view;
  }
 else   if (sectionRow.section() == SECTION_REFERRER_BREAKDOWN_LAYOUT) {
    return R.layout.dashboard_referrer_breakdown_layout;
  }
 else {
    return R.layout.dashboard_referrer_stats_view;
  }
}
