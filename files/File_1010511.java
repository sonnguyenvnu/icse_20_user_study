/*
 * Copyright 2003-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.library;

import gnu.trove.THashMap;
import gnu.trove.THashSet;
import jetbrains.mps.vfs.IFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Simple VFS tracker that knows about {@link org.jetbrains.mps.openapi.module.SModule modules} and {@link jetbrains.mps.vfs.IFile files} they originate
 * from and provides facilities that help to react with module reload/update according to VFS notifications  events.
 * Respects multiple modules per single file.
 * <p>
 * IMPLEMENTATION NOTE: not thread-safe
 *
 * @author Artem Tikhomirov
 * @since 3.5
 */
public final class ModuleFileTracker {
  private final Map<IFile, Set<SModuleReference>> myFile2Module = new THashMap<>();

  public ModuleFileTracker() {
  }

  /**
   * Associates given module with a file. Multiple modules per single file are allowed.
   * Multiple registration of the same File-Module pair is tolerated (XXX this is to avoid massive SLibrary refactoring, which may read same module and file).
   *
   * @param file   origin of a module
   * @param module module read from the file
   */
  public void track(@NotNull IFile file, @NotNull SModule module) {
    Set<SModuleReference> modules = myFile2Module.computeIfAbsent(file, k -> new THashSet<>());
    modules.add(module.getModuleReference());
  }

  /**
   * Discard tracked association between file and modules. Does nothing if no association for the file is known.
   *
   * @param file origin of a module or few modules
   */
  public void forget(@NotNull IFile file) {
    final Set<IFile> files2Remove = new THashSet<>();

    for (IFile moduleFile : myFile2Module.keySet()) {
      if (isAncestor(file, moduleFile)) {
        files2Remove.add(moduleFile);
      }
    }
    for (IFile moduleFile : files2Remove) {
      myFile2Module.remove(moduleFile);
    }
  }

  /**
   * Discard specific association between file and module. Does nothing if there's no such association.
   *
   * @param file   origin of the module
   * @param module module read from the file
   */
  public void forget(@NotNull IFile file, @NotNull SModule module) {
    forget(file, module.getModuleReference());
  }

  public void forget(@NotNull IFile file, @NotNull SModuleReference module) {
    Set<SModuleReference> modules = myFile2Module.get(file);
    if (modules == null) {
      return;
    }
    if (modules.remove(module)) {
      if (modules.isEmpty()) {
        myFile2Module.remove(file);
      }
    }
  }

  // unlike getTrackedFor, doesn't look at exact file matches, rather at paths with supplied files as ancestors
  // return pairs represent module and its originating file, note that the file may contain more than one module
  // i.e. that there might be few map entries with different keys but same value.
  // note, values of the returned map are not necessarily files of supplied collection, but exact module descriptor files recorded earlier with #track()
  // use Map here just not to use Pair<SModuleReference,IFile> or custom struct-like class, and to benefit from keySet().remove() that clears entries as well.
  public Map<SModuleReference, IFile> getAffectedBy(Collection<IFile> files) {
    // though we expect more than 1 module per file, we don't expect module to be in more than 1 file
    THashMap<SModuleReference, IFile> rv = new THashMap<>();
    for (IFile moduleFile : myFile2Module.keySet()) {
      // if this myFile2Module x files iteration turns out to be slow, consider isAncestor unwrap and pre-calculate Path objects for supplied files
      for (IFile f : files) {
        if (isAncestor(f, moduleFile)) {
          // assume each module is tracked only once in this MFT (i.e. no overwrite for rv keys)
          myFile2Module.get(moduleFile).forEach(mr -> rv.put(mr, moduleFile));
          // we don't care if the given moduleFile is affected by more than 1 file from supplied collection
          break;
        }
      }
    }
    return rv;
  }

  // tells if f2 resides under f1, i.e. if f1 is ancestor of f2
  // FIXME the method has to be part of IFile API. However, not clear whether we shall check for existence, how to approach canonical paths,
  //       and how to make sure we didn't error on 'file/pathLong'.startsWith('file/path'). Perhaps, would be better to have isAncestor
  //       for Path objects then (so that one knows it's not about existence or canonical)
  private static boolean isAncestor(IFile f1, IFile f2) {
    return f2.getPath().startsWith(f1.getPath());
  }


  // looks up tracked md files from supplied collection, and tells pairs <module, md file>
  public Map<SModuleReference, IFile> getTrackedFor(Collection<IFile> files) {
//    files.stream().flatMap(f -> myFile2Module.getOrDefault(f, Collections.emptySet()).stream()).collect(Collectors.toSet());
    final THashMap<SModuleReference, IFile> rv = new THashMap<>();
    for (IFile f : files) {
      final Set<SModuleReference> modules = myFile2Module.get(f);
      if (modules != null) {
        modules.forEach(mr -> rv.put(mr, f));
      }
    }
    return rv;
  }
}
