/** 
 * Updates with  {@link UpdateOptions}.
 */
public void update(UpdateOptions updateOptions){
  updateOptions.instructionsOption.ifPresent(s -> instructions=s);
  updateOptions.startTimeOption.ifPresent(s -> startTime=s);
  updateOptions.endTimeOption.ifPresent(s -> endTime=s);
  updateOptions.sessionVisibleFromTimeOption.ifPresent(s -> sessionVisibleFromTime=s);
  updateOptions.resultsVisibleFromTimeOption.ifPresent(s -> resultsVisibleFromTime=s);
  updateOptions.timeZoneOption.ifPresent(s -> timeZone=s);
  updateOptions.gracePeriodOption.ifPresent(s -> gracePeriod=s);
  updateOptions.sentOpenEmailOption.ifPresent(s -> sentOpenEmail=s);
  updateOptions.sentClosingEmailOption.ifPresent(s -> sentClosingEmail=s);
  updateOptions.sentClosedEmailOption.ifPresent(s -> sentClosedEmail=s);
  updateOptions.sentPublishedEmailOption.ifPresent(s -> sentPublishedEmail=s);
  updateOptions.isClosingEmailEnabledOption.ifPresent(s -> isClosingEmailEnabled=s);
  updateOptions.isPublishedEmailEnabledOption.ifPresent(s -> isPublishedEmailEnabled=s);
  updateOptions.addingStudentRespondentOption.ifPresent(s -> respondingStudentList.add(s));
  updateOptions.removingStudentRespondentOption.ifPresent(s -> respondingStudentList.remove(s));
  updateOptions.addingInstructorRespondentOption.ifPresent(s -> respondingInstructorList.add(s));
  updateOptions.removingInstructorRespondentOption.ifPresent(s -> respondingInstructorList.remove(s));
  updateOptions.updatingStudentRespondentOption.ifPresent(s -> {
    if (respondingStudentList.contains(s.getOldEmail())) {
      respondingStudentList.remove(s.getOldEmail());
      respondingStudentList.add(s.getNewEmail());
    }
  }
);
  updateOptions.updatingInstructorRespondentOption.ifPresent(s -> {
    if (respondingInstructorList.contains(s.getOldEmail())) {
      respondingInstructorList.remove(s.getOldEmail());
      respondingInstructorList.add(s.getNewEmail());
    }
  }
);
}
