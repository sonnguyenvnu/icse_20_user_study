public void render(HttpServletRequest req,HttpServletResponse resp,Object obj) throws Throwable {
  if (resp.getContentType() == null) {
    if (!Lang.isAndroid && obj != null && obj instanceof BufferedImage && "text/plain".equals(contentType)) {
      contentType=contentTypeMap.get("png");
    }
    resp.setContentType(contentType);
  }
  if (obj == null)   return;
  if (!Lang.isAndroid && obj instanceof BufferedImage) {
    OutputStream out=resp.getOutputStream();
    if (contentType.contains("png"))     ImageIO.write((BufferedImage)obj,"png",out);
 else     if (contentType.contains("webp"))     ImageIO.write((BufferedImage)obj,"webp",out);
 else     Images.writeJpeg((BufferedImage)obj,out,0.8f);
    return;
  }
 else   if (obj instanceof File) {
    File file=(File)obj;
    long fileSz=file.length();
    if (log.isDebugEnabled())     log.debug("File downloading ... " + file.getAbsolutePath());
    if (!file.exists() || file.isDirectory()) {
      log.debug("File downloading ... Not Exist : " + file.getAbsolutePath());
      resp.sendError(404);
      return;
    }
    if (!resp.containsHeader("Content-Disposition")) {
      String filename=URLEncoder.encode(file.getName(),Encoding.UTF8);
      resp.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
    }
    String rangeStr=req.getHeader("Range");
    OutputStream out=resp.getOutputStream();
    if (DISABLE_RANGE_DOWNLOAD || fileSz == 0 || (rangeStr == null || !rangeStr.startsWith("bytes=") || rangeStr.length() < "bytes=1".length())) {
      resp.setHeader("Content-Length","" + fileSz);
      Streams.writeAndClose(out,Streams.fileIn(file));
    }
 else {
      List<RangeRange> rs=new ArrayList<RawView.RangeRange>();
      if (!parseRange(rangeStr,rs,fileSz)) {
        resp.setStatus(416);
        return;
      }
      if (rs.size() != 1) {
        rs=rs.subList(0,1);
      }
      long totolSize=0;
      for (      RangeRange rangeRange : rs) {
        totolSize+=(rangeRange.end - rangeRange.start);
      }
      resp.setStatus(206);
      resp.setHeader("Content-Length","" + totolSize);
      resp.setHeader("Accept-Ranges","bytes");
      RangeRange rangeRange=rs.get(0);
      resp.setHeader("Content-Range",String.format("bytes %d-%d/%d",rangeRange.start,rangeRange.end - 1,fileSz));
      writeFileRange(file,out,rangeRange);
    }
  }
 else   if (obj instanceof byte[]) {
    resp.setHeader("Content-Length","" + ((byte[])obj).length);
    OutputStream out=resp.getOutputStream();
    Streams.writeAndClose(out,(byte[])obj);
  }
 else   if (obj instanceof char[]) {
    Writer writer=resp.getWriter();
    writer.write((char[])obj);
    writer.flush();
  }
 else   if (obj instanceof Reader) {
    Streams.writeAndClose(resp.getWriter(),(Reader)obj);
  }
 else   if (obj instanceof InputStream) {
    OutputStream out=resp.getOutputStream();
    Streams.writeAndClose(out,(InputStream)obj);
  }
 else {
    byte[] data=String.valueOf(obj).getBytes(Encoding.UTF8);
    resp.setHeader("Content-Length","" + data.length);
    OutputStream out=resp.getOutputStream();
    Streams.writeAndClose(out,data);
  }
}
