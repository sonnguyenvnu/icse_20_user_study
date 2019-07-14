public static void init(){
  Security.removeProvider("BC");
  Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(),Security.getProviders().length + 1);
  Security.insertProviderAt(new org.bouncycastle.jce.provider.BouncyCastleProvider(),Security.getProviders().length + 1);
}
