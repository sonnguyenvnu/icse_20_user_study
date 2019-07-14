private boolean checkSecretValues(byte[] passwordBytes,TLRPC.TL_account_passwordSettings passwordSettings){
  if (passwordSettings.secure_settings != null) {
    currentSecret=passwordSettings.secure_settings.secure_secret;
    byte[] passwordHash;
    if (passwordSettings.secure_settings.secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) {
      TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 algo=(TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000)passwordSettings.secure_settings.secure_algo;
      passwordHash=Utilities.computePBKDF2(passwordBytes,algo.salt);
    }
 else     if (passwordSettings.secure_settings.secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoSHA512) {
      TLRPC.TL_securePasswordKdfAlgoSHA512 algo=(TLRPC.TL_securePasswordKdfAlgoSHA512)passwordSettings.secure_settings.secure_algo;
      passwordHash=Utilities.computeSHA512(algo.salt,passwordBytes,algo.salt);
    }
 else {
      return false;
    }
    currentSecretId=passwordSettings.secure_settings.secure_secret_id;
    byte[] key=new byte[32];
    System.arraycopy(passwordHash,0,key,0,32);
    byte[] iv=new byte[16];
    System.arraycopy(passwordHash,32,iv,0,16);
    Utilities.aesCbcEncryptionByteArraySafe(currentSecret,key,iv,0,currentSecret.length,0,0);
    if (!PassportActivity.checkSecret(passwordSettings.secure_settings.secure_secret,passwordSettings.secure_settings.secure_secret_id)) {
      TLRPC.TL_account_updatePasswordSettings req=new TLRPC.TL_account_updatePasswordSettings();
      req.password=getNewSrpPassword();
      req.new_settings=new TLRPC.TL_account_passwordInputSettings();
      req.new_settings.new_secure_settings=new TLRPC.TL_secureSecretSettings();
      req.new_settings.new_secure_settings.secure_secret=new byte[0];
      req.new_settings.new_secure_settings.secure_algo=new TLRPC.TL_securePasswordKdfAlgoUnknown();
      req.new_settings.new_secure_settings.secure_secret_id=0;
      req.new_settings.flags|=4;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      }
);
      currentSecret=null;
      currentSecretId=0;
    }
  }
 else {
    currentSecret=null;
    currentSecretId=0;
  }
  return true;
}
