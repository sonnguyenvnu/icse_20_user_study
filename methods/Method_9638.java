public static void initPasswordNewAlgo(TLRPC.TL_account_password password){
  if (password.new_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
    TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)password.new_algo;
    byte[] salt=new byte[algo.salt1.length + 32];
    Utilities.random.nextBytes(salt);
    System.arraycopy(algo.salt1,0,salt,0,algo.salt1.length);
    algo.salt1=salt;
  }
  if (password.new_secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) {
    TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 algo=(TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000)password.new_secure_algo;
    byte[] salt=new byte[algo.salt.length + 32];
    Utilities.random.nextBytes(salt);
    System.arraycopy(algo.salt,0,salt,0,algo.salt.length);
    algo.salt=salt;
  }
}
