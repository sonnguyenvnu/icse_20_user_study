@Override public Rendering render(MailLinkNode node){
  String obfuscated=obfuscate(node.getText());
  return (new Rendering(obfuscate("mailto:") + obfuscated,obfuscated)).withAttribute("class",obfuscate("mail-link"));
}
