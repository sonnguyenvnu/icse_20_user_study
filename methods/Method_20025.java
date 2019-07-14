/** 
 * Create a random Rating POJO.
 */
public static Rating getRandom(){
  Rating rating=new Rating();
  Random random=new Random();
  double score=random.nextDouble() * 5.0;
  String text=REVIEW_CONTENTS[(int)Math.floor(score)];
  rating.setUserId(UUID.randomUUID().toString());
  rating.setUserName("Random User");
  rating.setRating(score);
  rating.setText(text);
  return rating;
}
