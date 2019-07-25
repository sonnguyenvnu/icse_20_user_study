/** 
 * Updates with  {@link UpdateOptions}.
 */
public void update(UpdateOptions updateOptions){
  updateOptions.shortNameOption.ifPresent(s -> shortName=s);
  updateOptions.emailOption.ifPresent(s -> email=s);
  updateOptions.instituteOption.ifPresent(s -> institute=s);
  updateOptions.nationalityOption.ifPresent(s -> nationality=s);
  updateOptions.genderOption.ifPresent(s -> gender=s);
  updateOptions.moreInfoOption.ifPresent(s -> moreInfo=s);
  updateOptions.pictureKeyOption.ifPresent(s -> pictureKey=s);
}
