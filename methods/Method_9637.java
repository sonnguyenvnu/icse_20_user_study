public static boolean canHandleCurrentPassword(TLRPC.TL_account_password password,boolean login){
  if (login) {
    if (password.current_algo instanceof TLRPC.TL_passwordKdfAlgoUnknown) {
      return false;
    }
  }
 else {
    if (password.new_algo instanceof TLRPC.TL_passwordKdfAlgoUnknown || password.current_algo instanceof TLRPC.TL_passwordKdfAlgoUnknown || password.new_secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoUnknown) {
      return false;
    }
  }
  return true;
}
