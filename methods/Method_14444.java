public static void gzipTarToOutputStream(Project project,OutputStream os) throws IOException {
  GZIPOutputStream gos=new GZIPOutputStream(os);
  TarOutputStream tos=new TarOutputStream(gos);
  try {
    ProjectManager.singleton.exportProject(project.id,tos);
  }
  finally {
    tos.close();
    gos.close();
  }
}
