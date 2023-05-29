package builder;

import java.util.HashMap;
import java.util.Map;

public class MyMapBuilder<K, V> implements MapBuilder<K, V>{
    private Map<K, V> innerMap;

    @Override
    public MyMapBuilder<K, V> entry(K key, V value){
        if(innerMap == null){
            this.innerMap = new HashMap<>();
        }
        innerMap.put(key, value);
        return this;
    }
    @Override
    public Map<K, V> build() {
        return new HashMap<>(this.innerMap);
    }
}
