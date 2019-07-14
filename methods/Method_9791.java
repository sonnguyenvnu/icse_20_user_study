/** 
 * Checks whether the specified captcha is invalid.
 * @param captcha the specified captcha
 * @return {@code true} if it is invalid, returns {@code false} otherwise
 */
public static boolean invalidCaptcha(final String captcha){
  if (StringUtils.isBlank(captcha) || captcha.length() != CAPTCHA_LENGTH) {
    return true;
  }
  boolean ret=!CaptchaProcessor.CAPTCHAS.contains(captcha);
  if (!ret) {
    CaptchaProcessor.CAPTCHAS.remove(captcha);
  }
  return ret;
}
