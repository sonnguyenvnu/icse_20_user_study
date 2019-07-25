/*
 * Copyright 2003-2019 JetBrains s.r.o.
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

import jetbrains.mps.RuntimeFlags;
import jetbrains.mps.classloading.ClassLoaderManager;
import jetbrains.mps.classloading.DeployListener;
import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.module.ReloadableModule;
import jetbrains.mps.project.Solution;
import jetbrains.mps.smodel.Generator;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.adapter.ids.MetaIdByDeclaration;
import jetbrains.mps.smodel.adapter.ids.MetaIdHelper;
import jetbrains.mps.smodel.adapter.ids.SLanguageId;
import jetbrains.mps.smodel.runtime.ModuleRuntime;
import jetbrains.mps.smodel.runtime.ModuleRuntime.ModuleRuntimeContext;
import jetbrains.mps.util.CollectionUtil;
import jetbrains.mps.util.NameUtil;
import jetbrains.mps.util.annotation.ToRemove;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.util.ProgressMonitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

import static java.lang.String.format;

/**
 * Preferred way to obtain instance of {@code LanguageRegistry} is to query {@link jetbrains.mps.components.ComponentHost}, e.g.
 * through {@code jetbrains.mps.core.platform.Platform} or {@link jetbrains.mps.project.Project#getComponent(Class)} which is aware of the MPS platform.
 *
 * evgeny, 3/11/11
 */
public class LanguageRegistry implements CoreComponent, DeployListener {
  private static final Logger LOG = LogManager.getLogger(LanguageRegistry.class);

  private static LanguageRegistry INSTANCE;

  /**
   * @deprecated obtain instance through {@link jetbrains.mps.components.ComponentHost#findComponent(Class) componentHost#findComponent(LanguageRegistry.class)}
   *             or use context-specific alternative {@link #getInstance(SRepository)}.
   */
  @Deprecated
  @ToRemove(version = 3.3)
  public static LanguageRegistry getInstance() {
    return INSTANCE;
  }

  /**
   * IMPORTANT: use {@link jetbrains.mps.components.ComponentHost#findComponent(Class) componentHost#findComponent(LanguageRegistry.class)} whenever \
   * possible instead of this method. USE OF THIS METHOD IS DISCOURAGED.
   * <p/>
   * At the moment, there's only 1 global LanguageRegistry. However, we move slowly towards independent
   * projects/non-global module repositories and thus would need repository-specific registries,
   * and use of the method is the proper way to  obtain registry and to think about proper
   * context in the client code right away.
   *
   * @return collection of languages available in the given context
   */
  @NotNull
  public static LanguageRegistry getInstance(@NotNull SRepository repository) {
    return INSTANCE;
  }

  private final Map<SLanguageId, LanguageRuntime> myLanguagesById = new HashMap<>();

  private final Map<SModuleReference, GeneratorRuntime> myGeneratorsWithCompiledRuntime = new HashMap<>();

  private final Map<SModuleReference, ModuleRuntime> myModuleRuntime = new HashMap<>();

  /*
   * Don't want to expose this lock right now, although perhaps would need to do it later, to facilitate scenarios with
   * LanguageRegistry that are not satisfied with withAvailableLanguages (e.g. span longer lifecycle).
   * At the moment, LR is updated inside repository's write action, and grabs myRuntimeInstanceAccess's write lock
   * as well, which may lead to deadlock  (myRuntimeInstanceAccess.read + MA.read vs MA.write+myRuntimeInstanceAccess.write from another thread)
   * and eventually we may split registry update out from model write, to run later, after write. Just need to sort out {@link LanguageRegistryListener}
   * contract that tells events are dispatched in [write] (didn't find anyone to rely on this, though). With a split, we can mitigate deadlock risk.
   */
  private final ReadWriteLock myRuntimeInstanceAccess = new ReentrantReadWriteLock();

  private final List<LanguageRegistryListener> myLanguageListeners = new CopyOnWriteArrayList<>();

  private final ClassLoaderManager myClassLoaderManager;

  public LanguageRegistry(ClassLoaderManager loaderManager) {
    myClassLoaderManager = loaderManager;
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }
    INSTANCE = this;
    myClassLoaderManager.addListener(this);
  }

  @Override
  public void dispose() {
    myClassLoaderManager.removeListener(this);
    INSTANCE = null;
  }

  private void notifyUnload(final Collection<LanguageRuntime> languages) {
    if (languages.isEmpty()) {
      return;
    }

    for (LanguageRegistryListener l : myLanguageListeners) {
      try {
        l.beforeLanguagesUnloaded(languages);
      } catch (Exception ex) {
        LOG.error(format("Exception on language unloading; languages: %s; listener: %s", languages, l), ex);
      }
    }
  }

  private void notifyLoad(final Collection<LanguageRuntime> languages) {
    if (languages.isEmpty()) {
      return;
    }

    for (LanguageRegistryListener l : myLanguageListeners) {
      try {
        l.afterLanguagesLoaded(languages);
      } catch (Exception ex) {
        LOG.error(format("Exception on language loading; languages: %s; listener: %s", languages, l), ex);
      }
    }
  }

  @Nullable
  private static LanguageRuntime createRuntime(Language l) {
    final String rtClassName = l.getModuleName() + ".Language";
    // Here, we consider few cases:
    // (a) there's no LR class
    // (b) there's legacy LR class (if we did changes to LR this release)
    // (c) LR in accordance with actual MPS version
    // Both (b) and (c) may fail during class-loading, which we treat as invalid language, although
    // for legacy versions and careless class evolution we might face otherwise valid languages which
    // fail to load due to class validation errors.
    // We aim to support binary compatibility between any two subsequent releases, thus failures for (b)
    // shall serve as an indicator we failed to maintain binary compatibility between releases
    try {
      final Class<?> rtClass = l.getOwnClass(rtClassName);
      if (LanguageRuntime.class.isAssignableFrom(rtClass)) {
        return rtClass.asSubclass(LanguageRuntime.class).newInstance();
      }
      if (RuntimeFlags.isUseInterpretedLanguages()) {
        LOG.error(String.format("Incompatible language runtime class for module %s; resort to interpreted runtime", l.getModuleName()));
        return new InterpretedLanguageRuntime(l);
      } else {
        LOG.error(String.format("Incompatible language runtime class for module %s; returning null", l.getModuleName()));
        return null;
      }
    } catch (ClassNotFoundException ex) {
      // would like to have error + exception here, but there are tests (e.g. ModulesReloadTest) that legitimately expect non-compiled modules
      // and no distinction between source and deployed modules. Now, we try to load any module added to the global repository, even if it's
      // a source module just added to a project. Once we tell deployed modules from sources (two distinct repositories would likely suffice),
      // AND LanguageRegistry listens to the proper one, we can have an error here.
      if (RuntimeFlags.isUseInterpretedLanguages()) {
        LOG.debug(String.format("Missing language runtime class for module %s (make failed?); resort to interpreted runtime", l.getModuleName()));
        return new InterpretedLanguageRuntime(l);
      } else {
        LOG.warn(String.format("Missing language runtime class for module %s (make failed?); returning null", l.getModuleName()));
        return null;
      }
    } catch (InstantiationException | IllegalAccessException e) {
      LOG.error(String.format("Failed to load language %s", l.getModuleName()), e);
      return null;
    }
  }

  /**
   * We expect Generator RT class for any generator module, both interpreted and generated.
   * However, in 2018.1 we tolerate no activator class case for compatibility with code generated with previous MPS releases.
   * In this case, we resort to TemplateModuleInterpreted supplied from LanguageRuntime#getGenerators().
   * <p/>
   * Note, there's an entanglement with TemplateModuleInterpreted/TemplateModuleInterpreted2, they don't work well when last.
   * Either doesn't track model/module changes and may answer with stale info if the instance stays for a long time.
   * Prior to 2018.1, approach was to ask language for generators (LR.getGenerators(), where new instance is created),
   * and LR+TMI assume no changes in generator module while these generators are consumed.
   * However, with activator class of generator module being there unless regenerated/reloaded, we may face issues, when template model is not in sync with
   * generated code.
   */
  private GeneratorRuntime createRuntime(Generator g) {
    SLanguage sourceLanguage = g.sourceLanguage();
    // A bit of history. The need for activator class name arise when we generate a code from a Generator module and load it at runtime.
    // First, there were no activators at all. Then, activators for generator modules with 'generated' templates were introduced.
    // They resorted to g.getSourceLanguage().getModuleName() + ".Generator", likely, not to deal with '#' in generator module name, and the fact
    // that generator module name left to '#' is identical to source language anyway. E.g. GeneratorDescriptorModelProvider assumed that
    // hash-less namespace of generator module was the same as source language. This was wrong, however, as there are generators that don't follow this
    // pattern, and attempt to load activator class like getSourceLanguage().getModuleName() + ".Generator" would fail for them. It's time to get this fixed.
    // Now, with activators generated for any Generator module,
    // we have to deal with a case when there are multiple generators in a language (well, we had to deal with it anyway, just didn't encounter it for
    // 'generated' templates). The idea is to use true name of generator module for activator class (except for '#...' suffix) as there seems to be no
    // solid ground to keep left-hand side of generator module name equal to that of its source language. Provided it was derived from the language,
    // existing compiled code (i.e. 'generated' generators) won't get broken as the value would be the same. There were no activators for generator modules
    // with interpreted templates, therefore it's the right time to introduce the change.
    // This code mimics GeneratorDescriptorModelProvider#getModelReference(), which controls name of a descriptor model (and, therefore, output location of
    // the activator class which is generated from the model).
    String mn = g.getModuleName();
    int hashSign = mn.indexOf('#');
    if (hashSign > 0) {
      mn = mn.substring(0, hashSign);
    }
    // we used to mangle name with ordinal index of generator in its language (getSourceLanguage().getOwnedGenerators().indexOf(this))
    // when there are few generators, but I don't think it's reasonable any more - it's easier to keep distinct generator modules in separate
    // namespaces rather than to guess activator name based on index in uncontrolled collection. Besides, generating two+ modules into same location
    // of their source language fools Make and leads to removal of activator of one of generated modules (same dir but file is not touched).
//    ArrayList<Generator> ownedGenerators = new ArrayList<>();
//    if (ownedGenerators.size() > 0 && ownedGenerators.indexOf(this) >= 0) {
//      className += ownedGenerators.indexOf(this);
//    }
    final String rtClassName = NameUtil.longNameFromNamespaceAndShortName(mn, "Generator");

    try {
      Class<?> rtClass = g.getOwnClass(rtClassName);
      if (GeneratorRuntime.class.isAssignableFrom(rtClass)) {
        final Class<? extends GeneratorRuntime> aClass = rtClass.asSubclass(GeneratorRuntime.class);
        final LanguageRuntime sourceLanguageRuntime = getLanguage(sourceLanguage);
        if (sourceLanguageRuntime == null) {
          throw new InstantiationException(String.format("Could not find language runtime for %s to attach generator %s to", sourceLanguage.getQualifiedName(),
              g.getModuleName()));
        }
        Constructor<?>[] allConstructors = aClass.getConstructors();
        // Provisional constructor for TemplateModule, that takes LanguageRegistry, LanguageRuntime and Generator module. @since 2018.1
        // It's to substitute new TemplateModuleInterpreted from the LanguageRuntime subclass. We need it until all the generators are completely
        // generated. Meanwhile we have a 'partially' interpreted generators, with TemplateModule/GeneratorRuntime being generated always.
        for (Constructor<?> cons : allConstructors) {
          if (cons.getParameterCount() != 3) {
            continue;
          }
          Class<?>[] parameterTypes = cons.getParameterTypes();
          if (parameterTypes[0] == LanguageRegistry.class && parameterTypes[1] == LanguageRuntime.class && parameterTypes[2] == Generator.class) {
            //noinspection JavaReflectionMemberAccess
            Constructor<? extends GeneratorRuntime> c = aClass.getConstructor(LanguageRegistry.class, LanguageRuntime.class, Generator.class);
            return c.newInstance(this, sourceLanguageRuntime, g);
          }
        }
        // Constructor for TemplateModule, the one that takes LanguageRegistry and LanguageRuntime. @since 2017.1
        for (Constructor<?> cons : allConstructors) {
          if (cons.getParameterCount() != 2) {
            continue;
          }
          Class<?>[] parameterTypes = cons.getParameterTypes();
          if (parameterTypes[0] == LanguageRegistry.class && parameterTypes[1] == LanguageRuntime.class) {
            //noinspection JavaReflectionMemberAccess
            Constructor<? extends GeneratorRuntime> c = aClass.getConstructor(LanguageRegistry.class, LanguageRuntime.class);
            return c.newInstance(this, sourceLanguageRuntime);
          }
        }
        LOG.error(String.format("No proper constructor found in the class %s of generator %s", rtClassName, g.getModuleName()));
        return null;
      } else {
        LOG.error(String.format("Generator runtime class %s from module %s is not an instance of GeneratorRuntime", rtClass.getName(), g.getModuleName()));
        return null;
      }
    } catch (ClassNotFoundException e) {
      String msg = format("Failed to load runtime %s of generator %s", rtClassName, g.getModuleName());
      if (g.generateTemplates()) {
        if (LOG.isDebugEnabled()) {
          LOG.warn(msg, e);
        } else {
          LOG.warn(msg);
        }
      } else {
        // FIXME this is compatibility code. Language RT generated prior to 2018.1 included #getGenerators() implementation for interpreted templates,
        //       and generator module lacked any activator class. With 2018.1, we generate Generator RT class for every generator module (including interpreted)
        //       Remove this code once 2018.1 is out.
        LOG.debug(msg, e);
      }
    } catch (InstantiationException | IllegalAccessException e) {
      LOG.error(String.format("Failed to instantiate runtime %s of generator %s", rtClassName, g.getModuleName()), e);
    } catch (NoSuchMethodException | InvocationTargetException e) {
      LOG.error(String.format("Failed to instantiate runtime %s of generator %s. Bad constructor?", rtClassName, g.getModuleName()), e);
    }
    return null;
  }

  @Nullable
  private ModuleRuntime createRuntime(Solution solution) {
    return new ModuleRuntime(solution.getModuleReference(), solution.getClassLoader());
  }

  public String toString() {
    return "LanguageRegistry";
  }

  public void addRegistryListener(LanguageRegistryListener listener) {
    myLanguageListeners.add(listener);
  }

  public void removeRegistryListener(LanguageRegistryListener listener) {
    myLanguageListeners.remove(listener);
  }

  /**
   * Synchronous access to actual languages in the registry.
   * It's guaranteed no change to the set of languages happen while this method is working.
   * BEWARE, {@code operation} shall not perform model read/write as it might lead to dead-lock
   * (a thread starts model write and waits for write on myRuntimeInstanceAccess, while another thread had grabbed
   * myRuntimeInstanceAccess read lock and consumer operation trues to grab model lock).
   * @param operation invoked for each actual {@link LanguageRuntime}, minimalistic and simple.
   */
  public void withAvailableLanguages(@NotNull Consumer<LanguageRuntime> operation) {
    try {
      myRuntimeInstanceAccess.readLock().lock();
      myLanguagesById.values().forEach(operation);
    } finally {
      myRuntimeInstanceAccess.readLock().unlock();
    }
  }

  /**
   * @return snapshot of languages known to the registry at the given moment.
   *         May not reflect actual state (a language might get unloaded), but as long as it's about identity objects, it's not that important to
   *         keep the collection exact.
   */
  public Collection<SLanguage> getAllLanguages() {
    ArrayList<SLanguage> rv = new ArrayList<>(100);
    withAvailableLanguages(lr -> rv.add(lr.getIdentity()));
    return rv;
  }

  @Nullable
  public LanguageRuntime getLanguage(SLanguage language) {
    return getLanguage(MetaIdHelper.getLanguage(language));
  }

  @Nullable
  public LanguageRuntime getLanguage(SLanguageId id) {
    try {
      myRuntimeInstanceAccess.readLock().lock();
      return myLanguagesById.get(id);
    } finally {
      myRuntimeInstanceAccess.readLock().unlock();
    }
  }

  @Nullable
  public LanguageRuntime getLanguage(String namespace) {
    try {
      myRuntimeInstanceAccess.readLock().lock();
      for (LanguageRuntime l : myLanguagesById.values()) {
        if (Objects.equals(l.getNamespace(), namespace)) {
          return l;
        }
      }
    } finally {
      myRuntimeInstanceAccess.readLock().unlock();
    }
    return null;
  }

  @Nullable
  public LanguageRuntime getLanguage(Language language) {
    return getLanguage(MetaIdByDeclaration.getLanguageId(language));
  }

  /**
   * PROVISIONAL API, DO NOT USE
   * Find respective runtime presentation of generator module
   * FIXME shall decide whether need standalone GeneratorRegistry to supply GeneratorRuntimes
   * FIXME or access to GeneratorRuntime through LanguageRegistry is enough.
   * @deprecated use {@link #getGenerator(SModuleReference)} as it's {@link SModuleReference} that is generator identity.
   */
  @Nullable
  @Deprecated
  @ToRemove(version = 2018.3)
  public GeneratorRuntime getGenerator(Generator generator) {
    return getGenerator(generator.getModuleReference());
  }

  /**
   *
   * @param generatorIdentity we use {@link SModuleReference} to identify generator, not to introduce a dedicated {@code SGenerator} similar to {@link SLanguage}
   */
  @Nullable
  public GeneratorRuntime getGenerator(@NotNull SModuleReference generatorIdentity) {
    // XXX Likely, shall guard with myRuntimeInstanceAccess once onLoad/onUnload guards generator modules
    return myGeneratorsWithCompiledRuntime.get(generatorIdentity);
  }


  // ClassLoaderManager/DeployListener part
  @Override
  public void onUnloaded(Set<ReloadableModule> unloadedModules, @NotNull ProgressMonitor monitor) {
    monitor.start("Solution Runtime", 5);
    ArrayList<ModuleRuntime> modulesToUnload = new ArrayList<>();
    for (Solution s : CollectionUtil.filter(Solution.class, unloadedModules)) {
      // get, not remove as we notify all first, and only then remove them.
      final ModuleRuntime moduleRuntime = myModuleRuntime.get(s.getModuleReference());
      if (moduleRuntime == null) {
        continue;
      }
      modulesToUnload.add(moduleRuntime);
    }
    ModuleRuntimeContext rtc = () -> null;
    for (ModuleRuntime rt : modulesToUnload) {
      rt.deactivate(rtc);
    }
    myModuleRuntime.values().removeAll(modulesToUnload);
    monitor.advance(1);

    monitor.step("Generator Runtime");
    for (Generator generator : collectGeneratorModules(unloadedModules)) {
      GeneratorRuntime generatorRuntime = myGeneratorsWithCompiledRuntime.remove(generator.getModuleReference());
      if (generatorRuntime == null) {
        // fine, we do not track GR other than generated
        // XXX Perhaps, with generator module RT for each generator, shall issue a warning like a language does, below
        continue;
      }
      LanguageRuntime srcLangRuntime = generatorRuntime.getSourceLanguage();
      srcLangRuntime.unregister(generatorRuntime);
    }
    monitor.advance(1);

    monitor.step("Language Runtime");
    Set<LanguageRuntime> languagesToUnload = new HashSet<>();
    for (Language language : collectLanguageModules(unloadedModules)) {
      SLanguageId sl = MetaIdByDeclaration.getLanguageId(language);
      if (!myLanguagesById.containsKey(sl)) {
        LOG.warn("No language with id " + sl + " to unload");
      } else {
        languagesToUnload.add(myLanguagesById.get(sl));
      }
    }
    monitor.advance(1);

    monitor.step("Language Registry Listeners");
    notifyUnload(languagesToUnload);
    monitor.advance(1);

    try {
      myRuntimeInstanceAccess.writeLock().lock();
      for (LanguageRuntime languageRuntime : languagesToUnload) {
        myLanguagesById.remove(languageRuntime.getId());
      }
      reinitialize();
    } finally {
      myRuntimeInstanceAccess.writeLock().unlock();
    }
    monitor.done();
  }

  @Override
  public void onLoaded(Set<ReloadableModule> loadedModules, @NotNull ProgressMonitor monitor) {
    monitor.start("Language Runtime", 5);
    Set<LanguageRuntime> loadedRuntimes = new LinkedHashSet<>();
    try {
      // FIXME why myRuntimeInstanceAccess doesn't guard instatiation of other module runtime classes?!
      myRuntimeInstanceAccess.writeLock().lock();
      for (Language language : collectLanguageModules(loadedModules)) {
        try {
          LanguageRuntime langRuntime = createRuntime(language);
          if (langRuntime == null) {
            continue;
          }
          SLanguageId sl = langRuntime.getId();
          if (myLanguagesById.containsKey(sl)) {
            String msg = String.format("There is already a language '%s'", myLanguagesById.get(sl));
            LOG.error(msg, new IllegalArgumentException(msg));
            continue;
          }
          myLanguagesById.put(sl, langRuntime);
          loadedRuntimes.add(langRuntime);
        } catch (LinkageError le) {
          processLinkageErrorForLanguage(language, le);
        }
      }
      reinitialize();
    } finally {
      myRuntimeInstanceAccess.writeLock().unlock();
    }
    monitor.advance(1);

    monitor.step("Generator Runtime");
    for (Generator generator : collectGeneratorModules(loadedModules)) {
      GeneratorRuntime generatorRuntime = createRuntime(generator);
      if (generatorRuntime == null) {
        // either interpreted or no generator at all, let generated LanguageRuntime#getGenerators() decide
        continue;
      }
      GeneratorRuntime old = myGeneratorsWithCompiledRuntime.put(generatorRuntime.getModuleReference(), generatorRuntime);
      if (old != null) {
        LOG.warn(String.format("There is already generator runtime for module '%s'", old.getModuleReference()));
      }
      LanguageRuntime srcLangRuntime = generatorRuntime.getSourceLanguage();
      srcLangRuntime.register(generatorRuntime);
    }
    monitor.advance(1);

    monitor.step("Solution Runtime");
    ArrayList<ModuleRuntime> loadedRuntime2 = new ArrayList<>();
    for (Solution s : CollectionUtil.filter(Solution.class, loadedModules)) {
      ModuleRuntime moduleRuntime = createRuntime(s);
      if (moduleRuntime == null) {
        continue;
      }
      ModuleRuntime old = myModuleRuntime.put(moduleRuntime.getSourceModule(), moduleRuntime);
      if (old != null) {
        LOG.warn(String.format("There's already runtime instance for module '%s'", old.getSourceModule()));
      }
      loadedRuntime2.add(moduleRuntime);
    }
    monitor.advance(1);

    monitor.step("Activators...");
    ModuleRuntimeContext rtc = () -> null;
    for (ModuleRuntime rt : loadedRuntime2) {
      rt.activate(rtc);
    }
    monitor.advance(1);

    monitor.step("Language Registry Listeners");
    // XXX perhaps, shall grab read lock of myRuntimeInstanceAccess? Or it's enough to assume we would never get into onLoaded again while we are not
    //     over with the previous one?
    notifyLoad(loadedRuntimes);
    monitor.advance(1);
    monitor.done();
  }

  private Collection<Language> collectLanguageModules(Set<? extends SModule> modules) {
    return CollectionUtil.filter(Language.class, modules);
  }

  private Collection<Generator> collectGeneratorModules(Set<? extends SModule> modules) {
    return CollectionUtil.filter(Generator.class, modules);
  }

  private void reinitialize() {
    myLanguagesById.values().forEach(LanguageRuntime::deinitialize);
    myLanguagesById.values().forEach(languageRuntime -> languageRuntime.initialize(this));
  }

  private static void processLinkageErrorForLanguage(Language language, LinkageError linkageError) {
    LOG.error("Caught a linkage error while creating LanguageRuntime for the `" + language + "` language." +
        "Probably the language sources/classes are outdated, try rebuilding the project.", linkageError);
    LOG.warn("MPS will attempt running in a inconsistent state.");
  }
}
