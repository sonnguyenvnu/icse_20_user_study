protected void stack(BodyWriter w,String heading,Throwable throwable){
  if (heading != null) {
    w.println("<div id=\"stack-header\">").println("<div class=\"wrapper\">").print("<h3>").escape(heading).print("</h3>").println("</div>").println("</div>");
  }
  w.println("<div class=\"wrapper\">").println("<div class=\"stack\">");
  w.print("<pre><code>");
  throwable(w,throwable,false);
  w.println("</pre></code>").println("</div>").println("</div>");
}
