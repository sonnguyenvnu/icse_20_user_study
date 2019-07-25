@WebkitCall public void paste(){
  EditorPane editorPane=current.currentEditor();
  Clipboard systemClipboard=Clipboard.getSystemClipboard();
  if (systemClipboard.hasFiles()) {
    Optional<String> block=parserService.toImageBlock(systemClipboard.getFiles());
    if (block.isPresent()) {
      editorPane.insert(block.get());
      return;
    }
  }
  if (systemClipboard.hasImage()) {
    Image image=systemClipboard.getImage();
    Optional<String> block=parserService.toImageBlock(image);
    if (block.isPresent()) {
      editorPane.insert(block.get());
      return;
    }
  }
  try {
    if (systemClipboard.hasHtml() || asciidocWebkitConverter.isHtml(systemClipboard.getString())) {
      String content=Optional.ofNullable(systemClipboard.getHtml()).orElse(systemClipboard.getString());
      if (current.currentTab().isAsciidoc() || current.currentTab().isMarkdown()) {
        content=(String)asciidocWebkitConverter.call(current.currentTab().htmlToMarkupFunction(),content);
      }
      editorPane.insert(content);
      return;
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  editorPane.execCommand("paste-raw-1");
}
