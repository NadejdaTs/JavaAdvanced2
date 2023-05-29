package builder;

import java.util.Map;

public interface MapBuilder<K, V> {
    Map<K, V> build();
    MapBuilder<K, V> entry(K key, V value);
}
