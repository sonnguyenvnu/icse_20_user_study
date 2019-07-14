public static @NonNull Reward rewardWithShipping(){
  return reward().toBuilder().shippingEnabled(true).shippingPreference("unrestricted").shippingSummary("Ships anywhere in the world").estimatedDeliveryOn(DateTime.parse("2019-03-26T19:26:09Z")).build();
}
