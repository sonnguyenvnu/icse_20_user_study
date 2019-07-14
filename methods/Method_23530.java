static protected void parseMTL(PApplet parent,String mtlfn,String path,BufferedReader reader,ArrayList<OBJMaterial> materials,Map<String,Integer> materialsHash){
  try {
    String line;
    OBJMaterial currentMtl=null;
    while ((line=reader.readLine()) != null) {
      line=line.trim();
      String parts[]=line.split("\\s+");
      if (parts.length > 0) {
        if (parts[0].equals("newmtl")) {
          String mtlname=parts[1];
          currentMtl=addMaterial(mtlname,materials,materialsHash);
        }
 else {
          if (currentMtl == null) {
            currentMtl=addMaterial("material" + materials.size(),materials,materialsHash);
          }
          if (parts[0].equals("map_Kd") && parts.length > 1) {
            String texname=parts[1];
            if (texname.indexOf(File.separator) == -1 && !path.equals("")) {
              texname=path + File.separator + texname;
            }
            File file=new File(parent.dataPath(texname));
            if (file.exists()) {
              currentMtl.kdMap=parent.loadImage(texname);
            }
 else {
              System.err.println("The texture map \"" + texname + "\" " + "in the materials definition file \"" + mtlfn + "\" " + "is missing or inaccessible, make sure " + "the URL is valid or that the file has been " + "added to your sketch and is readable.");
            }
          }
 else           if (parts[0].equals("Ka") && parts.length > 3) {
            currentMtl.ka.x=Float.valueOf(parts[1]).floatValue();
            currentMtl.ka.y=Float.valueOf(parts[2]).floatValue();
            currentMtl.ka.z=Float.valueOf(parts[3]).floatValue();
          }
 else           if (parts[0].equals("Kd") && parts.length > 3) {
            currentMtl.kd.x=Float.valueOf(parts[1]).floatValue();
            currentMtl.kd.y=Float.valueOf(parts[2]).floatValue();
            currentMtl.kd.z=Float.valueOf(parts[3]).floatValue();
          }
 else           if (parts[0].equals("Ks") && parts.length > 3) {
            currentMtl.ks.x=Float.valueOf(parts[1]).floatValue();
            currentMtl.ks.y=Float.valueOf(parts[2]).floatValue();
            currentMtl.ks.z=Float.valueOf(parts[3]).floatValue();
          }
 else           if ((parts[0].equals("d") || parts[0].equals("Tr")) && parts.length > 1) {
            currentMtl.d=Float.valueOf(parts[1]).floatValue();
          }
 else           if (parts[0].equals("Ns") && parts.length > 1) {
            currentMtl.ns=Float.valueOf(parts[1]).floatValue();
          }
        }
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
