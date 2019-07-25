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
package jetbrains.mps.smodel.language;

import jetbrains.mps.classloading.ClassLoaderManager;
import jetbrains.mps.classloading.DeployListener;
import jetbrains.mps.components.ComponentHost;
import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.module.ReloadableModule;
import jetbrains.mps.project.Solution;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.structure.ExtensionDescriptor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.util.ProgressMonitor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Registry of extensions populated by classes loaded from compiled and deployed modules
 */
public class ExtensionRegistry extends BaseExtensionRegistry implements CoreComponent {
  private static Logger LOG = LogManager.getLogger(ExtensionRegistry.class);

  private static ExtensionRegistry INSTANCE;

  private final HashMap<SModuleReference, ExtensionDescriptor> myExtensionDescriptors = new HashMap<>();
  private final ClassLoaderManager myClm;
  private final DeployListener myClassesListener = new DeployListener() {

    @Override
    public void onUnloaded(Set<ReloadableModule> unloadedModules, @NotNull ProgressMonitor monitor) {
      unloadExtensionDescriptors(unloadedModules, monitor);
    }

    @Override
    public void onLoaded(Set<ReloadableModule> loadedModules, @NotNull ProgressMonitor monitor) {
      loadExtensionDescriptors(loadedModules, monitor);
    }
  };

  /**
   * @deprecated avoid static access, replace with {@link ComponentHost#findComponent(Class) componentHost.findComponent(ExtensionRegistry.class)}
   */
  @Deprecated
  public static ExtensionRegistry getInstance() {
    return INSTANCE;
  }

  public ExtensionRegistry(@NotNull ClassLoaderManager clm) {
    myClm = clm;
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }
    myClm.addListener(myClassesListener);
    INSTANCE = this;
  }

  @Override
  public void dispose() {
    myClm.removeListener(myClassesListener);
    INSTANCE = null;
  }

  private void unloadExtensionDescriptors(Collection<ReloadableModule> unloadedModules, ProgressMonitor monitor) {
    monitor.start("Unregister extensions...", unloadedModules.size());
    for (SModule module : unloadedModules) {
      final ExtensionDescriptor desc = myExtensionDescriptors.remove(module.getModuleReference());
      if (desc != null) {
        unregisterExtensionDescriptor(desc);
      }
      monitor.advance(1);
    }
    monitor.done();
  }

  private void loadExtensionDescriptors(Collection<ReloadableModule> loadedModules, ProgressMonitor monitor) {
    monitor.start("Register extensions...", loadedModules.size());
    for (ReloadableModule module : loadedModules) {
      ExtensionDescriptor desc = findExtensionDescriptor(module);
      if (desc != null) {
        assert !myExtensionDescriptors.containsKey(module.getModuleReference()) : "Double registration of extensions for the same module";
        myExtensionDescriptors.put(module.getModuleReference(), desc);
        registerExtensionDescriptor(desc);
      }
      monitor.advance(1);
    }
    monitor.done();
  }

  private ExtensionDescriptor findExtensionDescriptor(SModule mod) {
    if (mod instanceof Language) {
      return findLanguageExtensionDescriptor((Language) mod);
    } else if (mod instanceof Solution) {
      switch (((Solution) mod).getKind()) {
        case PLUGIN_CORE:
        case PLUGIN_EDITOR:
        case PLUGIN_OTHER:
          return findPluginSolutionExtensionDescriptor((Solution) mod);

        default:
          break;
      }
    }
    return null;
  }

  private ExtensionDescriptor findPluginSolutionExtensionDescriptor(Solution solution) {
    // TODO: more flexible way of loading extensions from plugin solution
    String namespace = solution.getModuleName();
    String className = namespace + ".plugin.ExtensionDescriptor";
    Object compiled = getObjectByClassName(className, solution);
    if (compiled instanceof ExtensionDescriptor) {
      return (ExtensionDescriptor) compiled;
    }
    return null;
  }

  private ExtensionDescriptor findLanguageExtensionDescriptor(Language lang) {
    String namespace = lang.getModuleName();
    String className = namespace + ".plugin.ExtensionDescriptor";
    Object compiled = getObjectByClassName(className, lang);
    if (compiled instanceof ExtensionDescriptor) {
      return (ExtensionDescriptor) compiled;
    }
    return null;
  }

  @Nullable
  private static Object getObjectByClassName(String className, ReloadableModule module) {
    try {
      Class clazz = module.getOwnClass(className);
      return clazz.newInstance();
    } catch (Throwable e) {
      LOG.debug("error loading class\"" + className + "\"", e);
    }
    return null;
  }
}
