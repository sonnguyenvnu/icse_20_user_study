/** 
 * Configures the special css-specific extension for CSS files. <br/> The CSS extension is discontinued. CSS formatters are now part of the generic  {@link FormatExtension}.
 */
@Deprecated public void css(Action<CssExtension> closure){
  configure(CssExtension.NAME,CssExtension.class,closure);
}
