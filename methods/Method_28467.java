public List<ContributionsDay> getLastContributions(List<ContributionsDay> contributions){
  int lastWeekDays=contributions.size() % 7;
  int lastDays=(lastWeekDays > 0) ? lastWeekDays + (lastWeeks - 1) * 7 : lastWeeks * 7;
  return contributions.subList(contributions.size() - lastDays,contributions.size());
}
