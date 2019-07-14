private static void assertTrue(boolean checkValue) throws SubtitleDecoderException {
  if (!checkValue) {
    throw new SubtitleDecoderException("Unexpected subtitle format.");
  }
}
