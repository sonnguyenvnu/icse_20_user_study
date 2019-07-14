private static boolean isPCodedKey(short code){
  return code == com.jogamp.newt.event.KeyEvent.VK_UP || code == com.jogamp.newt.event.KeyEvent.VK_DOWN || code == com.jogamp.newt.event.KeyEvent.VK_LEFT || code == com.jogamp.newt.event.KeyEvent.VK_RIGHT || code == com.jogamp.newt.event.KeyEvent.VK_ALT || code == com.jogamp.newt.event.KeyEvent.VK_CONTROL || code == com.jogamp.newt.event.KeyEvent.VK_SHIFT || code == com.jogamp.newt.event.KeyEvent.VK_WINDOWS;
}
