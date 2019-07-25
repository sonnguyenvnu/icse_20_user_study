/*
 * Copyright 2003-2012 JetBrains s.r.o.
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

package jetbrains.mps.idea.core.project.stubs;

import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.roots.libraries.LibraryTable.Listener;
import jetbrains.mps.extapi.module.SRepositoryExt;

public abstract class BaseLibImporter extends AbstractJavaStubSolutionManager {
  private final Listener myListener = new MyListener();

  @Override
  protected void init() {
    getRepository().getModelAccess().runWriteAction(new Runnable() {
      @Override
      public void run() {
        for (Library l : getLibTable().getLibraries()) {
          addModuleForLibrary(l);
        }
      }
    });
    getLibTable().addListener(myListener);
  }

  @Override
  protected void dispose() {
    getLibTable().removeListener(myListener);
    super.dispose();
  }

  protected abstract SRepositoryExt getRepository();

  protected abstract LibraryTable getLibTable();

  protected void addModuleForLibrary(Library l) {
    addSolution(l, getRepository());
  }

  protected void removeModuleForLibrary(Library l) {
    removeSolution(l.getName(), getRepository());
  }

  private class MyListener implements Listener {
    public void afterLibraryAdded(final Library newLibrary) {
      getRepository().getModelAccess().runWriteAction(new Runnable() {
        public void run() {
          addModuleForLibrary(newLibrary);
        }
      });
    }

    public void afterLibraryRenamed(Library library) {
      //todo update models
    }

    public void beforeLibraryRemoved(final Library library) {
      getRepository().getModelAccess().runWriteAction(new Runnable() {
        public void run() {
          removeModuleForLibrary(library);
        }
      });
    }

    public void afterLibraryRemoved(Library library) {

    }
  }
}
