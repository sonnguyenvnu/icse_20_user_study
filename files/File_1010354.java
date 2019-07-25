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

import jetbrains.mps.smodel.BootstrapLanguages;
import jetbrains.mps.smodel.adapter.ids.SLanguageId;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.smodel.runtime.ILanguageAspect;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Runtime representation of a language, extension point for various language aspects.
 * Dependency from typesystem, find usages and other language aspects is transitional, eventually (after 3.2)
 * this class shall be generic and aware of {@link jetbrains.mps.smodel.runtime.ILanguageAspect} only.
 * It shall not load any classes through reflection (any class-loading of generated code/aspects is responsibility of
 * generated language runtime class).
 * <p/>
 * Language runtime keeps track of aspects queried (instantiates them lazily).
 */
public abstract class LanguageRuntime {
  private final ConcurrentMap<Class<? extends ILanguageAspect>, ILanguageAspect> myAspectDescriptors = new ConcurrentHashMap<>();
  private final List<LanguageRuntime> myExtendingLanguages = new ArrayList<>();
  private final List<LanguageRuntime> myExtendedLanguages = new ArrayList<>();

  /**
   * @return full name of the language, never {@code null}.
   */
  public abstract String getNamespace();

  /**
   * @return identity of the language, never {@code null}. Generated LanguageRuntime classes shall override return value
   */
  public abstract SLanguageId getId(); // FIXME supply a cons that takes mandatory values (id, name), rather than overriding methods in generated classes


  /**
   * @return persistable identity of a deployed language
   */
  public final SLanguage getIdentity() {
    // since 2018.2. XXX replace usages MetaAdapterFactory.getLanguage(langRuntime.getId, langRuntime.getNamespace) with this method
    return MetaAdapterFactory.getLanguage(getId(), getNamespace());
  }

  /**
   * Generated LanguageRuntime classes shall override this method
   * @since 3.2
   */
  public abstract int getVersion();

  public Collection<? extends GeneratorRuntime> getGenerators() {
    ArrayList<GeneratorRuntime> rv = new ArrayList<>(4);
    populateRegisteredGenerators(rv);
    return rv;
  }

  /**
   * Provide aspect instance associated with the language. Aspect is instantiated only once, lazily (the first time asked)
   * and the same instance is returned for each subsequent calls.
   * <p>
   * At the moment, sole mechanism to supply new aspect is code in generated language runtime subclass (i.e. there's no mechanism yet to
   * add aspects dynamically).
   * <p>
   * Calls {@link LanguageRuntimeAware#setLanguageRuntime(LanguageRuntime)} on aspects implementing {@link LanguageRuntimeAware} after creation, passing itself
   * as the parameter.
   *
   * @param aspectClass identifies aspect to retrieve
   * @param <T>         subtype of {@link jetbrains.mps.smodel.runtime.ILanguageAspect}
   * @return instance of aspect implementation if there's one for the language
   * @see #createAspect(Class)
   * @see jetbrains.mps.smodel.runtime.ILanguageAspect
   */
  public final <T extends ILanguageAspect> T getAspect(@NotNull Class<T> aspectClass) {
    try {
      T aspectDescriptor = aspectClass.cast(myAspectDescriptors.get(aspectClass));
      if (aspectDescriptor == null) {
        aspectDescriptor = createAspect(aspectClass);
        if (aspectDescriptor == null) {
          return null;
        }
        if (aspectDescriptor instanceof LanguageRuntimeAware) {
          ((LanguageRuntimeAware) aspectDescriptor).setLanguageRuntime(this);
        }
        T alreadyThere = aspectClass.cast(myAspectDescriptors.putIfAbsent(aspectClass, aspectDescriptor));
        if (alreadyThere != null) {
          return alreadyThere;
        }
      }
      return aspectDescriptor;
    } catch (Throwable th) {
      String msg = String.format("Failed to instantiate aspect %s in language %s", aspectClass, getNamespace());
      Logger.getLogger(LanguageRuntime.class).error(msg, th);
      return null;
    }
  }

  /**
   * Method every language shall implement to tell its capabilities.
   * Implementation doesn't need to keep state, {@link #getAspect(Class)} does that.
   * @param aspectClass never null identifying interface of the aspect
   * @param <T> aspect class
   * @return may return {@code null} indicating language has no such aspect
   */
  protected abstract <T extends ILanguageAspect> T createAspect(Class<T> aspectClass);

  /*
   * perhaps, could use WeakHashMap, although proper registration/un-registration sequence shall enforce no stale entries
   */
  private final Map<SModuleReference, GeneratorRuntime> myRegisteredGenerators = new HashMap<>();

  protected final void populateRegisteredGenerators(List<? super GeneratorRuntime> consumer) {
    consumer.addAll(myRegisteredGenerators.values());
  }

  /*package*/ void register(GeneratorRuntime runtime) {
    myRegisteredGenerators.put(runtime.getModuleReference(), runtime);
  }

  /*package*/ void unregister(GeneratorRuntime runtime) {
    myRegisteredGenerators.remove(runtime.getModuleReference());
  }

  /**
   * Closure of all languages that extend this one, exclusive.
   * FIXME why Iterable?
   *
   * @return unmodifiable collection of languages
   */
  @NotNull
  public Iterable<LanguageRuntime> getExtendingLanguages() {
    return myExtendingLanguages;
  }

  /**
   * Closure of all languages this language extends, exclusive.
   * Referenced languages are from the same LanguageRegistry as this one.
   * (Although there's only one LanguageRegistry at the moment, it's likely to change in the future)
   * <p/>
   * Collection captures only languages actually available, and might not reflect all dependencies of the language, i.e.
   * presents state of language relationship through a LanguageRegistry perspective. E.g. if language descriptor states 'extends' dependency
   * from a language missing in the LanguageRegistry instance, that extended language will be ignored and collection returned won't mention it.
   *
   * @return unmodifiable collection of languages
   */
  @NotNull
  public Collection<LanguageRuntime> getExtendedLanguages() {
    return Collections.unmodifiableCollection(myExtendedLanguages);
  }

  /**
   * Subclasses shall override to report languages they extend (fill in supplied collection).
   * It's not necessary to override the method if language doesn't extend any other, not to invoke super, this method is no-op.
   * <p/>
   * DESIGN NOTE: while it's sufficient to know SLanguageId only, I stick to SLanguage to keep namespace as debug information.
   *              Perhaps, shall pass an object that could take different alternatives (e.g. SLanguageId, (long,long), module reference)?
   */
  protected void fillExtendedLanguages(Collection<SLanguage> extendedLanguages) {
    // intentionally non-abstract and no-op
  }

  private void registerExtendingLanguage(LanguageRuntime extendingLanguage) {
    myExtendingLanguages.add(extendingLanguage);
    extendingLanguage.myExtendedLanguages.add(this);
  }

  void initialize(LanguageRegistry registry) {
    Queue<SLanguage> extendedLanguageIDs = new ArrayDeque<>();
    fillExtendedLanguages(extendedLanguageIDs);
    Set<SLanguageId> visitedLanguages = new HashSet<>();
    visitedLanguages.add(getId());
    while (!extendedLanguageIDs.isEmpty()) {
      SLanguage nextLanguageID = extendedLanguageIDs.remove();
      LanguageRuntime extendedLanguage = registry.getLanguage(nextLanguageID);
      if (extendedLanguage != null && visitedLanguages.add(extendedLanguage.getId())) {
        extendedLanguage.registerExtendingLanguage(this);
        extendedLanguage.fillExtendedLanguages(extendedLanguageIDs);
      }
    }
    // generally, should never happen, but doesn't hurt to ensure exclusive contract of getExtended/getExtendingLanguages()
    myExtendingLanguages.remove(this);
    myExtendedLanguages.remove(this);
    // Here's a copy of the hack from smodel.Language#getExtendedLanguageRefs(). We used to manifest lang.core
    // as extended language for any other language module, and once we switched to SLanguage, shall do the same at least for compatibility reasons.
    // Once we generate this extends inside #getExtendedLanguageIDs() (better the new one that yield SLanguage instead of String),
    // AND there are no old LanguageRuntime classes (i.e. past MPS 3.3), the hack shall cease to exist.
    //
    // XXX OTOH, does it make sense to force generation of explicit extends lang.core in each language?
    LanguageRuntime langCore = registry.getLanguage(BootstrapLanguages.getLangCore());
    assert langCore != null;
    if (this != langCore && !visitedLanguages.contains(langCore.getId())) {
      langCore.registerExtendingLanguage(this);
    }
  }

  void deinitialize() {
    myExtendingLanguages.clear();
    myExtendedLanguages.clear();
  }

  @Override
  public String toString() {
    return getNamespace() + " runtime";
  }
}
