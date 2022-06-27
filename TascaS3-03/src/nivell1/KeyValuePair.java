package nivell1;

import java.util.Map;

public class KeyValuePair {

    public KeyValuePair(String key, Map<String,Long> value) {
        this.key = key;
        this.value = value;
    }
    
    private String key;
    private Map<String, Long> value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Map<String, Long> getValue() {
		return value;
	}
	public void setValue(Map<String, Long> value) {
		this.value = value;
	}
    
    
}
