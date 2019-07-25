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
package jetbrains.mps.languageScope;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.smodel.SLanguageHierarchy;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.smodel.language.LanguageRegistryListener;
import jetbrains.mps.smodel.language.LanguageRuntime;
import jetbrains.mps.util.SimpleLRUCache;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: fyodor
 * Date: 8/27/12
 */
public class LanguageScopeFactory implements CoreComponent, LanguageRegistryListener {

  private static final int CACHE_SIZE = 1000;

  private static LanguageScopeFactory INSTANCE;

  // dependency
  private final LanguageRegistry myLanguageRegistry;

  /**
   * @deprecated
   */
  @Deprecated
  @ToRemove(version = 2018.2)
  public static LanguageScopeFactory getInstance() {
    return INSTANCE;
  }

  private ConcurrentHashMap<String, Integer> myNamespaceIndices = new ConcurrentHashMap<>();

  private AtomicInteger myBits = new AtomicInteger(0);

  private SimpleLRUCache<LanguagesHolder> myCachedLanguages;

  public LanguageScopeFactory(LanguageRegistry languageRegistry) {
    myLanguageRegistry = languageRegistry;
    initCache();
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }
    INSTANCE = this;
    myLanguageRegistry.addRegistryListener(this);
  }

  @Override
  public void dispose() {
    myCachedLanguages = null;
    myNamespaceIndices.clear();
    myLanguageRegistry.removeRegistryListener(this);
    INSTANCE = null;
  }

  @Override
  public void beforeLanguagesUnloaded(Iterable<LanguageRuntime> languages) {
    initCache();
  }

  @Override
  public void afterLanguagesLoaded(Iterable<LanguageRuntime> languages) {
    // do nothing
  }

  private void initCache() {
    myCachedLanguages = new SimpleLRUCache<LanguagesHolder>(CACHE_SIZE) {
      @Override
      protected void purged(LanguagesHolder holder) {
        holder.clear();
      }
    };
  }

  public int getIndexOf(String namespace) {
    if (!myNamespaceIndices.containsKey(namespace)) {
      myNamespaceIndices.putIfAbsent(namespace, myBits.getAndIncrement());
    }
    return myNamespaceIndices.get(namespace);
  }


  /**
   * Produces a new <code>LanguageScope</code> from the two corresponding to the parameters <code>langs1</code> and
   * <code>langs2</code> by merging.
   * @param langs1
   * @param langs2
   * @return
   */
  public LanguageScope getLanguageScope (Collection<SLanguage> langs1, Collection<SLanguage> langs2) {
    LanguageScope langScope1 = getLanguageScope(langs1);
    LanguageScope langScope2 = getLanguageScope(langs2);
    return langScope1.disjunction(langScope2);
  }

  /**
   * Produces a new <code>LanguageScope</code> from the ones corresponding to the collections in parameter <code>multiLangs</code>.
   * @param multiLangs
   * @return
   */
  public LanguageScope getMultiLanguageScope (Iterable<? extends Collection<SLanguage>> multiLangs) {
    LanguageScope langScope = null;
    for(Collection<SLanguage> langs: multiLangs) {
      LanguageScope tmp = getLanguageScope(langs);
      langScope = langScope == null? tmp : langScope.disjunction(tmp);
    }
    return langScope;
  }

  /**
   * This method implements a memoization scheme for the "extended languages hierarchy" using the specified collection of languages.
   * The objects constituting the contents of the  <code>langs</code> collection are expected to never change, and thus can be treated as opaque.
   * As a result, we can cache the calculated scope value with the key derived from objects representing the languages.
   * @param langs the dependencies collection; all languages included in this scope
   * @return
   */
  public LanguageScope getLanguageScope (Collection<SLanguage> langs) {
    LanguagesHolder cached = getHolder(langs);
    if (cached.hasScope()) {
      return cached.getScope();
    }

    BitSet nsBitSet = new BitSet(myBits.intValue());
    for (SLanguage lng: new SLanguageHierarchy(myLanguageRegistry, langs).getExtended()) {
      updateNamespaceBit(nsBitSet, lng.getQualifiedName());
    }
    LanguageScope langScope = new LanguageScope(this, nsBitSet);
    cached.setScope(langScope);
    return langScope;
  }

  private void updateNamespaceBit(BitSet nsBitSet, String namespace) {
    if (myNamespaceIndices.containsKey(namespace)) {
      nsBitSet.set(myNamespaceIndices.get(namespace));
    }
    else {
      myNamespaceIndices.putIfAbsent(namespace, myBits.getAndIncrement());
      nsBitSet.set(myNamespaceIndices.get(namespace));
    }
  }

  private LanguagesHolder getHolder(Collection<SLanguage> langs) {
    return myCachedLanguages.cacheObject(new LanguagesHolder(new ArrayList<>(langs)));
  }

  private static class LanguagesHolder {

    private SortedIdentityList<SLanguage> myWrappers;

    private LanguageScope myLangScope = null;

    public LanguagesHolder(Collection<SLanguage> langs)  {
      myWrappers = new SortedIdentityList<>(langs);
    }

    public boolean hasScope () {
      return myLangScope != null;
    }

    public void clear () {
      this.myLangScope = null;
    }

    public void setScope (LanguageScope langScope) {
      this.myLangScope = langScope;
    }

    public LanguageScope getScope () {
      return myLangScope;
    }

    @Override
    public boolean equals(Object that) {
      if (that == this) {
        return true;
      }
      return that instanceof LanguagesHolder && this.myWrappers.equals(((LanguagesHolder) that).myWrappers);
    }

    @Override
    public int hashCode() {
      return myWrappers.hashCode()*17 + 31;
    }
  }

  private static class SortedIdentityList<T> extends AbstractList<IdentityWrapper<T>> {

    private ArrayList<IdentityWrapper<T>> myList;

    public SortedIdentityList(Collection<T> original) {
      this.myList = new ArrayList<>(original.size());
      for (T o: original) {
        myList.add(new IdentityWrapper<>(o));
      }
      Collections.sort(myList);
    }

    @Override
    public IdentityWrapper<T> get(int index) {
      return myList.get(index);
    }

    @Override
    public int size() {
      return myList.size();
    }
  }

  private static class IdentityWrapper<K> implements Comparable<IdentityWrapper<K>> {

    private final K myObject;
    private final int myHash;

    public IdentityWrapper(K k) {
      myObject = k;
      myHash = k != null ? System.identityHashCode(myObject) : 113;
    }

    public K unwrap () {
      return myObject;
    }

    @Override
    public int compareTo(@NotNull IdentityWrapper<K> that) {
      return this.myHash - that.myHash;
    }

    @Override
    public int hashCode() {
      return myHash;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object that) {
      if (that == this) {
        return true;
      }
      return that instanceof IdentityWrapper && this.myObject == ((IdentityWrapper<K>) that).myObject;
    }
  }
}
