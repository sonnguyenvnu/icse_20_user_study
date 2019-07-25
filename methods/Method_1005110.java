/** 
 * ????
 * @param request
 * @param response
 * @throws IOException
 */
@RequestMapping(params="export") public void export(HttpServletRequest request,HttpServletResponse response) throws IOException {
  request.setCharacterEncoding("utf-8");
  response.setCharacterEncoding("utf-8");
  String type=request.getParameter("type");
  String svg=request.getParameter("svg");
  String filename=request.getParameter("filename");
  filename=filename == null ? "chart" : filename;
  ServletOutputStream out=response.getOutputStream();
  try {
    if (null != type && null != svg) {
      svg=svg.replaceAll(":rect","rect");
      String ext="";
      Transcoder t=null;
      if (type.equals("image/png")) {
        ext="png";
        t=new PNGTranscoder();
      }
 else       if (type.equals("image/jpeg")) {
        ext="jpg";
        t=new JPEGTranscoder();
      }
 else       if (type.equals("application/pdf")) {
        ext="pdf";
        t=(Transcoder)new PDFTranscoder();
      }
 else       if (type.equals("image/svg+xml"))       ext="svg";
      response.addHeader("Content-Disposition","attachment; filename=" + new String(filename.getBytes("GBK"),"ISO-8859-1") + "." + ext);
      response.addHeader("Content-Type",type);
      if (null != t) {
        TranscoderInput input=new TranscoderInput(new StringReader(svg));
        TranscoderOutput output=new TranscoderOutput(out);
        try {
          t.transcode(input,output);
        }
 catch (        TranscoderException e) {
          out.print("Problem transcoding stream. See the web logs for more details.");
          e.printStackTrace();
        }
      }
 else       if (ext.equals("svg")) {
        OutputStreamWriter writer=new OutputStreamWriter(out,"UTF-8");
        writer.append(svg);
        writer.close();
      }
 else       out.print("Invalid type: " + type);
    }
 else {
      response.addHeader("Content-Type","text/html");
      out.println("Usage:\n\tParameter [svg]: The DOM Element to be converted." + "\n\tParameter [type]: The destination MIME type for the elment to be transcoded.");
    }
  }
  finally {
    if (out != null) {
      out.flush();
      out.close();
    }
  }
}
