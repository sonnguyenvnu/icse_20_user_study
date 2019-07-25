private Integer percent(Integer completedSections){
  return Math.round(((float)completedSections / getTotalParts()) * 100);
}
