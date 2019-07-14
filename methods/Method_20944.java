public static User noRecommendations(){
  return user().toBuilder().optedOutOfRecommendations(true).build();
}
