/** 
 * Takes a CSS style block and consumes up to the first empty line found. Attempts to parse the contents of the style block and returns a  {@link WebvttCssStyle} instance if successful, or{@code null} otherwise.
 * @param input The input from which the style block should be read.
 * @return A {@link WebvttCssStyle} that represents the parsed block.
 */
public WebvttCssStyle parseBlock(ParsableByteArray input){
  stringBuilder.setLength(0);
  int initialInputPosition=input.getPosition();
  skipStyleBlock(input);
  styleInput.reset(input.data,input.getPosition());
  styleInput.setPosition(initialInputPosition);
  String selector=parseSelector(styleInput,stringBuilder);
  if (selector == null || !BLOCK_START.equals(parseNextToken(styleInput,stringBuilder))) {
    return null;
  }
  WebvttCssStyle style=new WebvttCssStyle();
  applySelectorToStyle(style,selector);
  String token=null;
  boolean blockEndFound=false;
  while (!blockEndFound) {
    int position=styleInput.getPosition();
    token=parseNextToken(styleInput,stringBuilder);
    blockEndFound=token == null || BLOCK_END.equals(token);
    if (!blockEndFound) {
      styleInput.setPosition(position);
      parseStyleDeclaration(styleInput,style,stringBuilder);
    }
  }
  return BLOCK_END.equals(token) ? style : null;
}
