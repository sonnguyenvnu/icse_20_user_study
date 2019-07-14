@Provides @Singleton Stripe provideStripe(final @ApplicationContext @NonNull Context context,final @NonNull ApiEndpoint apiEndpoint){
  final String stripePublishableKey=apiEndpoint == ApiEndpoint.PRODUCTION ? Secrets.StripePublishableKey.PRODUCTION : Secrets.StripePublishableKey.STAGING;
  return new Stripe(context,stripePublishableKey);
}
