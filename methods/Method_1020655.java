@Bean public DefaultKaptcha producer(){
  Properties properties=new Properties();
  properties.put(KAPTCHA_BORDER,SecurityConstants.DEFAULT_IMAGE_BORDER);
  properties.put(KAPTCHA_TEXTPRODUCER_FONT_COLOR,SecurityConstants.DEFAULT_COLOR_FONT);
  properties.put(KAPTCHA_TEXTPRODUCER_CHAR_SPACE,SecurityConstants.DEFAULT_CHAR_SPACE);
  properties.put(KAPTCHA_IMAGE_WIDTH,SecurityConstants.DEFAULT_IMAGE_WIDTH);
  properties.put(KAPTCHA_IMAGE_HEIGHT,SecurityConstants.DEFAULT_IMAGE_HEIGHT);
  properties.put(KAPTCHA_IMAGE_FONT_SIZE,SecurityConstants.DEFAULT_IMAGE_FONT_SIZE);
  properties.put(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH,SecurityConstants.DEFAULT_IMAGE_LENGTH);
  Config config=new Config(properties);
  DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
  defaultKaptcha.setConfig(config);
  return defaultKaptcha;
}
