/** 
 * Updates with  {@link UpdateOptions}.
 */
public void update(UpdateOptions updateOptions){
  updateOptions.newEmailOption.ifPresent(s -> email=s);
  updateOptions.nameOption.ifPresent(s -> {
    name=s;
    lastName=StringHelper.splitName(s)[1];
  }
);
  updateOptions.lastNameOption.ifPresent(s -> lastName=s);
  updateOptions.commentOption.ifPresent(s -> comments=s);
  updateOptions.googleIdOption.ifPresent(s -> googleId=s);
  updateOptions.teamNameOption.ifPresent(s -> team=s);
  updateOptions.sectionNameOption.ifPresent(s -> section=s);
}
