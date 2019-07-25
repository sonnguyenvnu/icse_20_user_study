package jetbrains.mps.util.containers;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Map from key to multiple values
 * By default the collection (which is stored as a value in the map) is <code>java.util.List</code>-based.
 * Thus the order is preserved
 *
 * @author Dmitry Avdeev
 */
public class MultiMap<K, V> implements Serializable {
  private static final long serialVersionUID = -2632269270151455493L;

  public static final MultiMap EMPTY = new MultiMap() {
    @Override
    protected Map createMap() {
      return Collections.emptyMap();
    }
  };

  private final Map<K, Collection<V>> myMap;
  private Collection<V> values;

  public MultiMap() {
    myMap = createMap();
  }

  public MultiMap(int i, float v) {
    myMap = createMap(i, v);
  }

  protected Map<K, Collection<V>> createMap() {
    return new HashMap<>();
  }

  protected Map<K, Collection<V>> createMap(int i, float v) {
    return new HashMap<>(i, v);
  }

  protected Collection<V> createCollection() {
    return new ArrayList<>();
  }

  protected Collection<V> createEmptyCollection() {
    return Collections.emptyList();
  }

  public <Kk extends K, Vv extends V> void putAllValues(MultiMap<Kk, Vv> from) {
    for (Map.Entry<Kk, Collection<Vv>> entry : from.entrySet()) {
      putValues(entry.getKey(), entry.getValue());
    }
  }

  public void putValues(K key, Collection<? extends V> values) {
    Collection<V> list = myMap.get(key);
    if (list == null) {
      list = createCollection();
      myMap.put(key, list);
    }
    list.addAll(values);
  }

  public void putValue(K key, V value) {
    Collection<V> list = myMap.get(key);
    if (list == null) {
      list = createCollection();
      myMap.put(key, list);
    }
    list.add(value);
  }

  public Set<Entry<K, Collection<V>>> entrySet() {
    return myMap.entrySet();
  }

  public boolean isEmpty() {
    if (myMap.isEmpty()) return true;

    for (Collection<V> valueList : myMap.values()) {
      if (!valueList.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  public boolean containsKey(K key) {
    return myMap.containsKey(key);
  }

  public boolean containsScalarValue(V value) {
    for (Collection<V> valueList : myMap.values()) {
      if (valueList.contains(value)) {
        return true;
      }
    }
    return false;
  }

  @NotNull
  public Collection<V> get(final K key) {
    final Collection<V> collection = myMap.get(key);
    return collection == null ? createEmptyCollection() : collection;
  }

  @NotNull
  public Collection<V> getModifiable(final K key) {
    Collection<V> collection = myMap.get(key);
    if (collection == null) {
      myMap.put(key, collection = createCollection());
    }
    return collection;
  }

  public Set<K> keySet() {
    return myMap.keySet();
  }

  public int size() {
    return myMap.size();
  }

  public void put(final K key, final Collection<V> values) {
    myMap.put(key, values);
  }

  public void removeValue(final K key, final V value) {
    final Collection<V> values = myMap.get(key);
    if (values != null) {
      values.remove(value);
      if (values.isEmpty()) {
        myMap.remove(key);
      }
    }
  }

  public Collection<? extends V> values() {
    if (values == null) {
      values = new AbstractCollection<V>() {
        @NotNull
        @Override
        public Iterator<V> iterator() {
          return new Iterator<V>() {

            private Iterator<Collection<V>> mapIterator = myMap.values().iterator();

            private Iterator<V> itr = EmptyIterator.getInstance();

            @Override
            public boolean hasNext() {
              do {
                if (itr.hasNext()) return true;
                if (!mapIterator.hasNext()) return false;
                itr = mapIterator.next().iterator();
              } while (true);
            }

            @Override
            public V next() {
              do {
                if (itr.hasNext()) return itr.next();
                if (!mapIterator.hasNext()) throw new NoSuchElementException();
                itr = mapIterator.next().iterator();
              } while (true);
            }

            @Override
            public void remove() {
              itr.remove();
            }
          };
        }

        @Override
        public int size() {
          int res = 0;
          for (Collection<V> vs : myMap.values()) {
            res += vs.size();
          }

          return res;
        }

        // Don't remove this method!!!
        @Override
        public boolean contains(Object o) {
          for (Collection<V> vs : myMap.values()) {
            if (vs.contains(o)) return true;
          }

          return false;
        }
      };
    }

    return values;
  }

  public void clear() {
    myMap.clear();
  }

  public Collection<V> remove(K key) {
    return myMap.remove(key);
  }

  @NotNull
  public static <K, V> MultiMap<K, V> emptyInstance() {
    @SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"}) final MultiMap<K, V> empty = EMPTY;
    return empty;
  }

  @NotNull
  public static <K, V> MultiMap<K, V> create() {
    return new MultiMap<>();
  }

  @NotNull
  public static <K, V> MultiMap<K, V> create(int i, float v) {
    return new MultiMap<>(i, v);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MultiMap)) return false;
    return myMap.equals(((MultiMap) o).myMap);
  }

  @Override
  public int hashCode() {
    return myMap.hashCode();
  }

  @Override
  public String toString() {
    return myMap.toString();
  }

  public void putAllValues(Map<K, Collection<V>> map) {
    for (Entry<K, Collection<V>> e : map.entrySet()) {
      putValues(e.getKey(), e.getValue());
    }
  }
}
